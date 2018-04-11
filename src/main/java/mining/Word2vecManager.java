package mining;

import mining.word2vec.DocVectorModel;
import mining.word2vec.WordVectorModel;
import utils.Property;

import java.io.IOException;

public class Word2vecManager {
    private static Word2vecManager instance ;
    public static Word2vecManager getInstance() {
        if (instance == null) {
            synchronized (Word2vecManager.class) {
                if (instance == null) {
                    instance = new Word2vecManager();
                }
            }
        }
        return instance;
    }

    private static final String WORD2VEC_MODEL_PATH = Property.WORD2VEC_MODEL_PATH;
    private WordVectorModel wordModel;
    private DocVectorModel docModel;

    private Word2vecManager() {
        try {
            wordModel = new WordVectorModel(WORD2VEC_MODEL_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        docModel = new DocVectorModel(wordModel);
    }

    public DocVectorModel getDocModel() {
        return docModel;
    }

    public WordVectorModel getWordModel() {
        return wordModel;
    }
}
