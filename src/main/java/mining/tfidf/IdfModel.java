package mining.tfidf;

import java.io.IOException;
import java.util.Map;

public class IdfModel {

    private Map<String, Float> idfMap;

    public IdfModel(String path) throws IOException{
        this.idfMap = this.loadIdfMap(path);
    }

    private Map<String, Float> loadIdfMap(String path) throws IOException {

    }
}
