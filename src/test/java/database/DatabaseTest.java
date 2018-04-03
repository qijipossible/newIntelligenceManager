package database;

import database.entity.Record;

import java.util.Date;

public class DatabaseTest {
    public DatabaseTest() {
        Record record = (Record) HibernateUtil.findById(1);
        System.out.println(record.toString());
        System.out.println(record.getContent());
    }

    public static void main(String[] args) {
        new DatabaseTest();
    }
}
