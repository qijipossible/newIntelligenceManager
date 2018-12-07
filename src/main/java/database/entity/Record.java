package database.entity;

import java.util.Date;

import org.hibernate.annotations.*;

public class Record {
    private int id;
    private String url;
    private String title;
    private String content;
    private Date date;
    private String source;
    private String contentType;
    private String sourceType;
    private String keyword;
    private String summary;
    private String other;
    private int cluster;

    public Record() {
    }

    public Record(String url, String title, String content, Date date, String source, String contentType, String sourceType, String keyword, String summary, String other) {
        this.url = url;
        this.title = title;
        this.content = content;
        this.date = date;
        this.source = source;
        this.contentType = contentType;
        this.sourceType = sourceType;
        this.keyword = keyword;
        this.summary = summary;
        this.other = other;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public int getCluster() {
        return cluster;
    }

    public void setCluster(int cluster) {
        this.cluster = cluster;
    }

    public String toString() {
        return "[record " + id + ":" + title + "]";
    }

    /*
    url, title, content, date, source, contentType, sourceType, keyword, summary, other
    "url" ,"title" ,"content" ,"date" ,"source" ,"contentType" ,"sourceType" ,"keyword" ,"summary" ,"other"
     */

}
