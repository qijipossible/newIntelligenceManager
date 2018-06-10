package spider.processor;

import java.text.ParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;

import business.Parse;
import org.jsoup.Jsoup;

import spider.Configure;
import spider.utils.Inspector;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

public class TopicProcessor implements PageProcessor {

	private Inspector inspector = new Inspector();

	private Site site = Site.me()
			.setSleepTime(Configure.SPIDER_SLEEP_TIME);

	private String domain = null;

	public void process(Page page) {
		Html html = page.getHtml();

		// 解析链接，判定是否为当前域名链接
		if(domain == null){
			domain= site.getDomain();
		}
		List<String> links = html.links().all(); //获取当前html页面所有链接
		for (String link : links) {
			if (inspector.isUrlQualified(link) && link.contains(domain)) { //判断链接是否符合要求&&是否属于当前域名
				page.addTargetRequest(link);
			}
		}

		// 解析元素
		String url = page.getUrl().toString();
        System.out.print("page get: " + url + "\n");
		String title = Jsoup.parse(html.$("title").toString()).text().trim();
		System.out.print("title: " + title + "\n");
		if(Parse.isItInvalid(title)){ // 若标题无效则不必耗时去解析内容
			System.err.println("title is null, page skipped.\n");
			page.setSkip(true);
			return;
		}
		String content = smartContent(html.toString()).trim(); //XXX 主要内容提取

        List<String> mustContain = new ArrayList<String>();
        mustContain.add("四川");
        mustContain.add("军民融合");
        inspector.addMustContain(mustContain);
		// 判定是否有效，是否保留
		if(Parse.isAnyInvalid(url, title, content) || !inspector.isPageQualified(title, content)){
			System.err.println("not qualified, page skipped.\n");
			page.setSkip(true);
			return;
		}
		page.putField("url", url);
		page.putField("title", title);
		page.putField("content", content);

        String datetext;
		Pattern pattern = Pattern.compile("(20[0-1][0-9])[^.0-9]([0-1]?[0-9])[^.0-9]([0-3]?[0-9])");
		Matcher matcher = pattern.matcher(html.toString());
		if (matcher.find()) {
			String year = matcher.group(1);
			String month = matcher.group(2);
			String day = matcher.group(3);
			datetext = year + "-" + month + "-" + day;
		} else {
			pattern = Pattern.compile("([0-1]?[0-9])[^.0-9]([0-3]?[0-9])");
			matcher = pattern.matcher(html.toString());
			if (matcher.find()) {
				String year = "2017";
				String month = matcher.group(1);
				String day = matcher.group(2);
				datetext  = year + "-" + month + "-" + day;
			} else {
				System.err.print("无时间信息");
				page.setSkip(true);
				return;
			}
		}
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = sdf.parse(datetext);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        page.putField("date", date);
		
	}

	public Site getSite() {
		return site;
	}

	//获取正文内容
	private String smartContent(String html) {
		html = html.replaceAll("(?is)<!DOCTYPE.*?>", "");
		html = html.replaceAll("(?is)<!--.*?-->", "");				// remove html comment
		html = html.replaceAll("(?is)<script.*?>.*?</script>", ""); // remove javascript
		html = html.replaceAll("(?is)<style.*?>.*?</style>", "");   // remove css
		html = html.replaceAll("&.{2,5};|&#.{2,5};", " ");			// remove special char
		html = html.replaceAll("(?is)<a(.?[a-zA-Z-]*?=\".*?\")*>.*?</a>","");	//remove href

        //选出正文所在的div
        int maxCount_hanziInDiv = 0, max_index = 0;
        String[] div_list = findAllLabelX_HTML(html, "<div>");
        for(int i = 0; i < div_list.length; i++){
            int count = findRegexCount(div_list[i], "[\\u4e00-\\u9fa5]");
            if (count > maxCount_hanziInDiv){
                maxCount_hanziInDiv = count;
                max_index = i;
            }
            if (count == 300)
                break;
        }

        if (maxCount_hanziInDiv >= 100) {
            html = div_list[max_index];
        }
        //如果整个HTML中的汉字总数是所选div的五倍以上，说明该div并非正文所在div
        else {
            int count_hanziInHTML = findRegexCount(html, "[\\u4e00-\\u9fa5]");
            if (count_hanziInHTML / maxCount_hanziInDiv < 5 && count_hanziInHTML < 300)
                html = div_list[max_index];
        }

        //反选<p>……</p> 清掉除此之外所有
        String[] p_list = findAllLabelX_HTML(html, "<p>");
        if(!(p_list.length == 0)) { //存在汉字 不存在<p> 则不反选删除
            html = html.replaceAll("(?is)<p.*?>.*?</p>", "~~~");
            html = html.replaceAll("[^~\n]", "");
            for (String str : p_list) {
                html = html.replaceFirst("~~~", str);
            }
        }

		html = html.replaceAll("(?is)<.*?>", "");

		List<String> lines;
		int blocksWidth = 3;
		int threshold = 63;
        int wordsNum = 0;

		int start;
		int end;
		StringBuilder text = new StringBuilder();
		ArrayList<Integer> indexDistribution = new ArrayList<Integer>();
        start = -1; end = -1;
        boolean boolstart = false, boolend = false;
        text.setLength(0);

		lines = Arrays.asList(html.split("\n"));

		for (int i = 0; i <= lines.size() - blocksWidth; i++) {
		    wordsNum = 0;
			for (int j = i; j < i + blocksWidth; j++) {
				lines.set(j, lines.get(j).replaceAll("\\s+", ""));
				wordsNum += lines.get(j).length();
			}
			indexDistribution.add(wordsNum);
		}

        if (lines.size() > blocksWidth) { // <= 直接变成text返回
            //反选<p>后，正文可能位于最后，与下文冲突，故添加00
            for (int i = 0; i < blocksWidth - 1; i++) {
                indexDistribution.add(wordsNum);
            }
            indexDistribution.add(0);
            indexDistribution.add(0);
        }
        else{
		    for(String str : lines){
		        text.append(str).append("\n");
            }
        }


		for (int i = 0; i < indexDistribution.size() - 1; i++) {
			if (indexDistribution.get(i) > threshold && ! boolstart) {
				if (indexDistribution.get(i + 1) != 0
						|| indexDistribution.get(i + 2) != 0
						|| indexDistribution.get(i + 3) != 0) {
					boolstart = true;
					start = i;
					continue;
				}
			}
			if (boolstart) {
				if (indexDistribution.get(i) == 0
						|| indexDistribution.get(i + 1) == 0) {
					end = i;
					boolend = true;
				}
			}
			StringBuilder tmp = new StringBuilder();
			if (boolend) {
				//System.out.println(start+1 + "\t\t" + end+1);
				for (int ii = start; ii <= end; ii++) {
					if (lines.get(ii).length() < 5) continue;
					tmp.append(lines.get(ii)).append("\n");
				}
				String str = tmp.toString();
				//System.out.println(str);
				if (str.contains("Copyright") || str.contains("版权所有"))
					break;
				text.append(str);
				boolstart = boolend = false;
			}
		}
		return text.toString();
	}

	//查找并返回全部标签labelX内的内容
    private String[] findAllLabelX_HTML(final String html, String labelX ){
        //labelX需要带<>,例如："<p>", "<a>", "<div>", ...
        //返回一个全部labelX的数组
        int j = 0;
        String firstSplit = labelX.substring(0,labelX.length()-1) + ".*?>";
        String lastSplit = "</" + labelX.substring(1);
        if (!html.contains(labelX.subSequence(1,labelX.length()-1)))
            return new String[0];
        String[] tempList = html.split(firstSplit);
        String[] res = new String[tempList.length];
        for(int i = 1; i < tempList.length; i++){
            String[] tempList2 = tempList[i].split(lastSplit);
            if(tempList2.length != 0)
                res[j++] = tempList2[0];
        }
        for(; j < res.length; j++){
            res[j] = "";
        }
        return res;
    }

    //查找regex匹配的字符串个数
    private int findRegexCount(final String str,String regex){
	    int count = 0;
	    String str1, str2 = str;
	    do{
	        str1 = str2;
            str2 = str2.replaceFirst(regex, " ");
            count++;
            if(count > 300)
                return 300;
        }while(!str1.equals(str2));
	    return count - 1;
    }

}














