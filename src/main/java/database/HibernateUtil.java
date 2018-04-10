package database;

import java.io.Serializable;
import java.util.Iterator;

import business.Parse;
import database.entity.Record;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class HibernateUtil {
    private static SessionFactory sessionfactory;
    private HibernateUtil(){}
    static{
        Configuration cfg = new Configuration().configure();
        sessionfactory = cfg.buildSessionFactory();

        // 以下部分用来初始化数据库，将数据库字符集改为UTF-8，否则将不能存储中文
        // XXX 能否将以下初始化部分只在第一次运行
        Session session = sessionfactory.openSession();
        Transaction tx = session.beginTransaction();;
        String columns[] = new String[] {"url" ,"title" ,"content" ,"date" ,"source" ,"contentType" ,"sourceType" ,"keyword" ,"summary" ,"other"};
        Query query;
        for(String column : columns){
            String s = "ALTER TABLE `JMRH` CHANGE `"+column+"` `"+column+"` TEXT CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL;";
            query = session.createSQLQuery(s);
            query.executeUpdate();
        }
        tx.commit();
        session.close();
    }

    private static Session getSession(){
        return sessionfactory.openSession();
    }
    public static void close(){
        getSession().close();
        sessionfactory.close();
    }
    
// 以下为操作方法
    public static Iterator<Record> getAll(){
        Session session = HibernateUtil.getSession();
        Iterator<Record> records = session.createQuery("from Record a order by a.id asc").iterate();
        return records;
    }

    public static void parseAll(){
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
    }

    //添加  
    public static void save(Object obj){
        Session session = null;
        Transaction tx;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            session.save(obj);
            //session.persist(obj);
            // 区别：save()方法如果没有开启事务，它会执行一条插入语句，但之后由于没有提交事务，它又进行
            //回滚了,而persist()方法在没有开启事务的时候，它根本不会去执行，即没有那条插入语句
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if(session!=null){
                session.close();
            }
        }
    }
    //修改  
    public static void update(Object obj){
        Session session = null;
        Transaction tx;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        }finally{
            if(session!=null){
                session.close();
            }
        }
    }
    //删除  
    public static void delete(Object obj){
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            session.delete(obj);
            tx.commit();
        }finally{
            if(session!=null){
                session.close();
            }
        }
    }

    //查找 不需要开启事务
    public static Object findById(Serializable id) {

        Session session = null;
        try {
            session = HibernateUtil.getSession();
            //这里需要区分一下get()与load()的区别，load()不会立即去访问数据库只有在第一次使用的时候才会去加载（懒加载）；
            //load方法永远不可能返回空对象(如果不存在，其会产生一个user的子类)具体可以去查资料区别这两个方法  
            //Object obj = session.load(clazz, id);  
            return session.get(Record.class, id);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}