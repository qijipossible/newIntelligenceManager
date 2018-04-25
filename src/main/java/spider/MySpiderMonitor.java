package spider;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.SpiderListener;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.monitor.SpiderStatus;
import us.codecraft.webmagic.monitor.SpiderStatusMXBean;

import javax.management.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class MySpiderMonitor extends SpiderMonitor{
    private static MySpiderMonitor INSTANCE = new MySpiderMonitor();
    List<SpiderStatusMXBean> spiderStatuses = new ArrayList<SpiderStatusMXBean>();

    public synchronized MySpiderMonitor register(Spider... spiders) throws JMException {
        for (Spider spider : spiders) {
            SpiderMonitor.MonitorSpiderListener monitorSpiderListener = new SpiderMonitor.MonitorSpiderListener();
            if (spider.getSpiderListeners() == null) {
                List<SpiderListener> spiderListeners = new ArrayList<SpiderListener>();
                spiderListeners.add(monitorSpiderListener);
                spider.setSpiderListeners(spiderListeners);
            } else {
                spider.getSpiderListeners().add(monitorSpiderListener);
            }
            spiderStatuses.add(new SpiderStatus(spider, monitorSpiderListener));
        }
        return this;
    }

    public Map<String, Integer> getTotalPageCount(){
        int success = 0;
        int left = 0;
        for (SpiderStatusMXBean spiderStatus : spiderStatuses) {
            success += spiderStatus.getSuccessPageCount();
            left += spiderStatus.getLeftPageCount();
        }
        Map<String, Integer> countMap = new HashMap<String, Integer>();
        countMap.put("success", success);
        countMap.put("left", left);
        return countMap;
    }

    public static MySpiderMonitor instance() {
        return INSTANCE;
    }


}
