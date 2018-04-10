package business;

import com.hankcs.hanlp.HanLP;
import mining.word2vec.DocVectorModel;
import mining.word2vec.Vector;
import mining.word2vec.WordVectorModel;
import database.entity.Record;
import targetsites.SiteManager;

import java.io.IOException;
import java.util.*;

public class Parse {

    private static final String WORD2VEC_MODEL_PATH = "./resources/data/word2vec/hanlp-wiki-vec-zh.txt";
    private static WordVectorModel wordModel;
    private static DocVectorModel docModel;
    static {
        try {
            wordModel = new WordVectorModel(WORD2VEC_MODEL_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        docModel = new DocVectorModel(wordModel);
    }

    public static boolean ParseRecord(Record record){
        System.out.println("Parsing record:" + record.toString());
        if(record.getUrl() == null) throw new NullPointerException();

        int id = record.getId();
        String url = record.getUrl();
        String content = record.getContent();
        if(content.equals("")) return false;

        //严格去重
        Vector vector;
        if(record.getOther() == null){
             vector = docModel.query(content);
        }else{
            vector = new Vector(record.getOther());
        }
        List<Map.Entry<Integer, Float>> nearest = docModel.nearest(vector, 1);
        if(!nearest.isEmpty()){
            if(1.0 - nearest.get(0).getValue() < 0.000001){
                return false;
            }
        }

        docModel.addDocument(id, vector);
        record.setOther(vector.toString());

        if(record.getSource() == null)
            record.setSource(url2source(url));
//            System.out.println(record.getSource());
        if(record.getSourceType() == null)
            record.setSourceType(url2sourceType(url));
//            System.out.println(record.getSourceType());
        if(record.getKeyword() == null)
            record.setKeyword(content2keyword(content));
//            System.out.println(record.getKeyword());
        if(record.getSummary() == null)
            record.setSummary(content2summary(content));
//            System.out.println(record.getSummary());
        // TODO contentType

        return true;
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
