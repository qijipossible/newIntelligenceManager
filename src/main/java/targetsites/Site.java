package targetsites;

import utils.UrlUtils;

public class Site {
    private String url;
    private String name;
    private String type;
    private String domain;

    public Site(String url, String name) {
        this.url = url;
        this.name = name;
        this.type = getType();
        this.domain = getDomain();
    }

    public Site(String url, String name, String type) {
        this.url = url;
        this.name = name;
        this.type = type;
        this.domain = getDomain();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDomain() {
        if(domain == null){
            domain = UrlUtils.getDomain(url);
        }
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        if(type == null){
            type = url.contains(".gov.cn") ? "官方网站" : "网络新闻";
        }
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
