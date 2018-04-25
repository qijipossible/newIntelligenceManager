package spider;

import spider.pipeline.MysqlPipeline;
import spider.processor.TopicProcessor;
import targetsites.SiteManager;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderStatusMXBean;

import javax.management.JMException;
import java.util.List;
import java.util.Map;

public class SpiderManager {

    private String[] siteUrlArray;
    private int siteNum;
    private Spider[] spiderList;
    private MySpiderMonitor mySpiderMonitor;

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
        mySpiderMonitor = MySpiderMonitor.instance();
    }

    public void start(){
        for(int i=0;i<siteNum;i++){
            spiderList[i] = Spider
                    .create(new TopicProcessor())
                    .addUrl(siteUrlArray[i])
                    .addPipeline(new MysqlPipeline())
                    .thread(1);
            try {
                mySpiderMonitor.register(spiderList[i]);
            } catch (JMException e) {
                e.printStackTrace();
            }
            spiderList[i].start();
        }
    }

    public void stop(){
        for (Spider spider : spiderList) {
            if(spider != null) spider.stop();
        }
    }

    public Map<String, Integer> getPageCountMap(){
        return mySpiderMonitor.getTotalPageCount();
    }
}
