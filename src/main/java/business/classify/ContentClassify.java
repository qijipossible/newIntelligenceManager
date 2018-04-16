package business.classify;

import com.hankcs.hanlp.classification.classifiers.IClassifier;
import com.hankcs.hanlp.classification.models.NaiveBayesModel;
import com.hankcs.hanlp.corpus.io.IOUtil;
import utils.Property;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ContentClassify {

    private static final String CORPUS_FOLDER = Property.CLASSIFIER_PATH;
    private static final String MODEL_FILE_PATH = Property.MODEL_FILE_PATH;

    static void predict(String text) throws IOException{
        NaiveBayesModel model;
        IClassifier classifier;

        if (new File(MODEL_FILE_PATH).exists()){
            model = (NaiveBayesModel) IOUtil.readObjectFrom(MODEL_FILE_PATH);
            classifier = new MyClassifier(model);
        }
        else{
            File corpusFolder = new File(CORPUS_FOLDER);
            if (!corpusFolder.exists() || !corpusFolder.isDirectory()) {
                System.err.println("没有文本分类语料");
            }

            classifier = new MyClassifier(); // 创建分类器，更高级的功能请参考IClassifier的接口定义
            classifier.train(CORPUS_FOLDER);                     // 训练后的模型支持持久化，下次就不必训练了
//            model = (NaiveBayesModel) classifier.getModel();
//            IOUtil.saveObjectTo(model, MODEL_FILE_PATH);
        }
        Map<String, Double> result = classifier.predict(text);
        System.out.print(result.toString());
    }
}
