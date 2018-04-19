package UI;

import database.DatabaseManager;
import database.entity.Record;
import spider.SpiderManager;

import java.util.List;

public class UIHelper {
    private DatabaseManager databaseManager = DatabaseManager.getInstance();
    private SpiderManager spiderManager = SpiderManager.getInstance();

    public void startSpider(){
        spiderManager.start();
    }

    public List<Record> getAll(){
        return databaseManager.getAll();
    }

    public void parseAll(){
        databaseManager.parseAll();
    }
}
