package business;

import com.hankcs.hanlp.HanLP;
import database.entity.Record;
import targetsites.SiteManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parse {

    public static Record ParseRecord(Record record){
        System.out.println("Parsing record:" + record.toString());
        if(record.getUrl() == null) throw new NullPointerException();

        String url = record.getUrl();
        String content = record.getContent();

        if(record.getSource() != null) record.setSource(url2source(url));
        if(record.getSourceType() != null) record.setSourceType(url2sourceType(url));
        if(record.getKeyword() != null) record.setKeyword(content2keyword(content));
        if(record.getSummary() != null) record.setSummary(content2summary(content));
        // TODO contentType

        return record;
    }

    private static SiteManager siteManager = SiteManager.getInstance();

    public static String url2source(String url){
        return siteManager.url2name(url);
    }

    public static String url2sourceType(String url){
        return siteManager.url2type(url);
    }

    public static String content2keyword(String content){
        List<String> keywords = HanLP.extractKeyword(content, 5);
        for(String each_keyword: keywords){
            if(each_keyword.contains("军民") || each_keyword.contains("融合")) keywords.remove(each_keyword);
        }
        return keywords.toString();
    }

    public static String content2summary(String content){
        List<String> summary = HanLP.extractSummary(content, 3);
        return summary.toString();
    }

//    public static String content2contentType(String content){
//        //TODO 解析内容类型
//    }
}
