package spider;

import spider.pipeline.MysqlPipeline;
import spider.processor.TopicProcessor;
import utils.FileUtils;
import us.codecraft.webmagic.Spider;

public class SpiderTest {
    public SpiderTest() {
        String[] siteList = FileUtils.readSiteList(".//resources//sitelist.txt").toArray(new String[0]);
        int siteNum = siteList.length;
        Spider[] spiderList = new Spider[siteNum];
        for(int i=0;i<siteNum;i++){
            spiderList[i] = Spider
                    .create(new TopicProcessor())
                    .addUrl(siteList[i])
                    .addPipeline(new MysqlPipeline())
                    .thread(1);
            spiderList[i].start();
        }
    }

    public static void main(String[] args) {
        new SpiderTest();
    }
}
