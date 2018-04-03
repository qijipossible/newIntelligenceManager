package business;

import database.HibernateUtil;
import database.entity.Record;

import java.util.Iterator;

public class ParseTest {
    private ParseTest() {
        HibernateUtil.updateAll();
    }

    public static void main(String[] args) {
        new ParseTest();
    }
}
