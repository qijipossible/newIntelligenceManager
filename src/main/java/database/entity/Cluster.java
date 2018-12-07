package database.entity;

import java.util.ArrayList;

public class Cluster {
    private String clusterAbstract;
    private int id;
    private ArrayList<Record> recordList;
    private String clusterKeyWord;

    public Cluster(String clusterAbstract, int id, ArrayList<Record> recordList, String clusterKeyWord) {
        this.clusterAbstract = clusterAbstract;
        this.id = id;
        this.recordList = recordList;
        this.clusterKeyWord = clusterKeyWord;
    }

    public Cluster() {
    }

    public String getClusterAbstract() {
        return clusterAbstract;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Record> getRecordList() {
        return recordList;
    }

    public String getClusterKeyWord() {
        return clusterKeyWord;
    }

    public void setClusterAbstract(String clusterAbstract) {
        this.clusterAbstract = clusterAbstract;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRecordList(ArrayList<Record> recordList) {
        this.recordList = recordList;
    }

    public void setClusterKeyWord(String clusterKeyWord) {
        this.clusterKeyWord = clusterKeyWord;
    }
}