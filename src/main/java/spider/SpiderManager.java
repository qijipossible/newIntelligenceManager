package spider;

import spider.pipeline.MysqlPipeline;
import spider.processor.TopicProcessor;
import targetsites.SiteManager;
import us.codecraft.webmagic.Spider;
import utils.FileUtils;

public class SpiderManager {

    private String[] siteUrlArray;
    private int siteNum;
    private Spider[] spiderList;

    private static SpiderManager instance ;
    public static SpiderManager getInstance() {
        if (instance == null) {
            synchronized (SpiderManager.class) {
                if (instance == null) {
                    instance = new SpiderManager();
                }
            }
        }
        return instance;
    }

    private SpiderManager(){
        siteUrlArray = SiteManager.getInstance().getSiteUrlArray();
        siteNum = siteUrlArray.length;
        spiderList = new Spider[siteNum];
    }

    public void start(){
        for(int i=0;i<siteNum;i++){
            spiderList[i] = Spider
                    .create(new TopicProcessor())
                    .addUrl(siteUrlArray[i])
                    .addPipeline(new MysqlPipeline())
                    .thread(1);
            spiderList[i].start();
        }
    }
}
