package business;

import com.hankcs.hanlp.HanLP;
import mining.Word2vecManager;
import mining.word2vec.DocVectorModel;
import mining.word2vec.Vector;
import database.entity.Record;
import targetsites.SiteManager;

import java.util.*;

public class Parse {
    private static SiteManager siteManager = SiteManager.getInstance();
    private static Word2vecManager word2vecManager = Word2vecManager.getInstance();
    private static DocVectorModel docModel = word2vecManager.getDocModel();

    public static boolean ParseRecord(Record record, boolean updateAnyway){
        System.out.println("Parsing record:" + record.toString());

        int id = record.getId();
        String url = record.getUrl();
        String title = record.getTitle();
        String content = record.getContent();
        if(isAnyInvalid(url, title, content)) return false;

        Vector vector;
        if(record.getOther() == null || "".equals(record.getOther())){
            vector = docModel.query(record.getContent());
            record.setOther(vector.toString());
        }else{
            vector = new Vector(record.getOther());
        }

        if(DuplicateRemove.isDuplicated(id, vector)) return false;

        if(updateAnyway || record.getSource() == null)
            record.setSource(url2source(url));
        if(updateAnyway || record.getSourceType() == null)
            record.setSourceType(url2sourceType(url));
        if(updateAnyway || record.getKeyword() == null)
            record.setKeyword(content2keyword(content));
        if(updateAnyway || record.getSummary() == null)
            record.setSummary(content2summary(content));
        // TODO contentType

        return true;
    }

    public static boolean ParseRecord(Record record){
        return ParseRecord(record, false);
    }

    public static boolean isItInvalid(String it){
        return it == null || "".equals(it);
    }

    public static boolean isAnyInvalid(String url, String title, String content){
        return isItInvalid(content) || isItInvalid(url) || isItInvalid(title);
    }

    public static String url2source(String url){
        return siteManager.url2name(url);
    }

    public static String url2sourceType(String url){
        return siteManager.url2type(url);
    }

    public static String content2keyword(String content){
        List<String> keywords = HanLP.extractKeyword(content, 5);
        for (int i = 0; i < keywords.size(); i++) {
            String each_keyword = keywords.get(i);
            if(each_keyword.contains("军民") || each_keyword.contains("融合"))
                keywords.remove(i);
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
