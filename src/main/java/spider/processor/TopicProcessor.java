package spider.processor;

import java.text.ParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		List<String> links = html.links().all();
		for (String link : links) {
			if (inspector.isUrlQualified(link) && link.contains(domain)) {
				page.addTargetRequest(link);
			}
		}

		// 解析元素
		String url = page.getUrl().toString();
        System.out.print("page get: " + url + "\n");
        page.putField("url", url);

		String title = Jsoup.parse(html.$("title").toString()).text().trim();
		System.out.print("title: " + title + "\n");
		if(title.equals("")){
			System.err.println("title is null, page skipped.\n");
			page.setSkip(true);
			return;
		}
		page.putField("title", title);

		String content = html.smartContent().get().trim(); //XXX 主要内容提取
		if(content.equals("") || !inspector.isPageQualified(title, content)){
			System.err.println("not qualified, page skipped.\n");
			page.setSkip(true);
			return;
		}
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
			pattern = Pattern.compile("([0-1]?[0-9])[^.0-9]([0-1]?[0-9])");
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
}
