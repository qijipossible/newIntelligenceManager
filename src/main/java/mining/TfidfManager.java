package mining;

import mining.tfidf.IdfModel;

public class TfidfManager {

    private static TfidfManager instance ;
    public static TfidfManager getInstance() {
        if (instance == null) {
            synchronized (TfidfManager.class) {
                if (instance == null) {
                    instance = new TfidfManager();
                }
            }
        }
        return instance;
    }

    private IdfModel idfModel;

    public TfidfManager() {
        this.idfModel = IdfModel();
    }
}
