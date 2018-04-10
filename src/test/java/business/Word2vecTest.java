package business;

import com.hankcs.hanlp.mining.word2vec.DocVectorModel;
import com.hankcs.hanlp.mining.word2vec.WordVectorModel;
import database.HibernateUtil;
import database.entity.Record;

import java.io.IOException;
import java.util.Iterator;

public class Word2vecTest {
    public static void main(String[] args) throws IOException {
        String modelPath = "./resources/data/word2vec/hanlp-wiki-vec-zh.txt";
        WordVectorModel wordModel = new WordVectorModel(modelPath);
        DocVectorModel docModel = new DocVectorModel(wordModel);
        Iterator<Record> records = HibernateUtil.getAll();
        while(records.hasNext()){
            Record cur = records.next();
            docModel.addDocument(cur.getId(), cur.getContent());
        }
        HibernateUtil.close();
        System.out.print(docModel.nearest(4990));
    }
}
