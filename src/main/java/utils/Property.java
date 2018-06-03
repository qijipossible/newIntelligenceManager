package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class Property {
    public static String RESOURCE_ROOT;

    static {
        Properties p = new Properties();
        try {
            p.load(Property.class.getResourceAsStream("/config.properties"));
            RESOURCE_ROOT = Property.class.getResource("/").getPath() + p.getProperty("ResourceRoot");
            Properties properties = new Properties();
            properties.put("root",RESOURCE_ROOT);
            FileOutputStream fos = new FileOutputStream(Property.class.getResource("/hanlp.properties").getPath());
            properties.store(fos, null);
            fos.close();
        } catch (IOException ignored) {
        }
        if(RESOURCE_ROOT == null || "".equals(RESOURCE_ROOT)) RESOURCE_ROOT = "./resources/";
    }

    public static final String HANLP_DATA_PATH = RESOURCE_ROOT + "data/";
    public static final String SITELIST_FILE_PATH = RESOURCE_ROOT + "sitelist.txt";
    public static final String WORD2VEC_MODEL_PATH = HANLP_DATA_PATH + "word2vec/hanlp-wiki-vec-zh.txt";
    public static final String CLASSIFIER_PATH = RESOURCE_ROOT + "classifier/";
    public static final String CORPUS_FOLDER =  RESOURCE_ROOT + "classifier";
    public static final String MODEL_FILE_PATH = CLASSIFIER_PATH + "classification-model.ser";
}
