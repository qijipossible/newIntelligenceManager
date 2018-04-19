package database;

import business.Parse;
import database.entity.Record;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DatabaseManager {

    private static DatabaseManager instance ;
    public static DatabaseManager getInstance() {
        if (instance == null) {
            synchronized (DatabaseManager.class) {
                if (instance == null) {
                    instance = new DatabaseManager();
                }
            }
        }
        return instance;
    }

    private List<Record> allRecords;
    
    public List<Record> getAll(){
        if (allRecords == null){
            Iterator<Record> recordIterator = HibernateUtil.getAll();
            allRecords = new ArrayList<Record>();
            while (recordIterator.hasNext())
                allRecords.add(recordIterator.next());
            HibernateUtil.closeSession();
        }
        return allRecords;
    }

    public void parseAll(){
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        Iterator records = session.createQuery("from Record a order by a.id asc").iterate();
        while(records.hasNext()) {
            Record record = (Record) records.next();
            if(Parse.ParseRecord(record)){
                session.update(record);
            }else {
                session.delete(record);
            }
        }
        tx.commit();
        session.close();
        allRecords = null; // 数据库内容有更新，则缓存失效，置为null
    }
}
