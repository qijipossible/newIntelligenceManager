package UI;

import database.DatabaseManager;
import database.entity.Record;
import spider.SpiderManager;

import java.util.List;
import java.util.Map;

public class UIHelper {
    private DatabaseManager databaseManager = DatabaseManager.getInstance();
    private SpiderManager spiderManager = SpiderManager.getInstance();

    public void startSpider(){
        spiderManager.start();
    }

    public void stopSpider(){
        spiderManager.stop();
    }

    public Map<String, Integer> getPageCountMap(){
        return spiderManager.getPageCountMap();
    }

    public List<Record> getAll(){
        return databaseManager.getAll();
    }

    public void parseAll(){
        databaseManager.parseAll();
    }
}
