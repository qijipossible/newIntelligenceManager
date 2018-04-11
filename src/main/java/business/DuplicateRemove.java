package business;

import database.entity.Record;
import mining.Word2vecManager;
import mining.word2vec.DocVectorModel;
import mining.word2vec.Vector;

import java.util.List;
import java.util.Map;

public class DuplicateRemove {
    private static Word2vecManager word2vecManager = Word2vecManager.getInstance();
    private static DocVectorModel docModel = word2vecManager.getDocModel();

    //严格去重
    public static boolean isDuplicated(int id, Vector vector){
        List<Map.Entry<Integer, Float>> nearest = docModel.nearest(vector, 1);
        if(!nearest.isEmpty()){
            if(1.0 - nearest.get(0).getValue() < 0.0000001){
                return true;
            }
        }
        docModel.addDocument(id, vector);
        return false;
    }
}
