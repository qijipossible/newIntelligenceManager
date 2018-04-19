package targetsites;

import utils.FileUtils;
import utils.Property;
import utils.UrlUtils;

import java.util.ArrayList;
import java.util.List;

public class SiteManager {
    private static SiteManager instance ;
    public static SiteManager getInstance() {
        if (instance == null) {
            synchronized (SiteManager.class) {
                if (instance == null) {
                    instance = new SiteManager();
                }
            }
        }
        return instance;
    }

    private List<String> siteStringList;
    private List<Site> siteList = new ArrayList<Site>();
    private SiteManager() {
        reload();
    }

    public void reload(){
        siteStringList = FileUtils.readSiteList(Property.SITELIST_FILE_PATH);
        // TODO 站点列表预处理，去重等

        for(String siteString: siteStringList){
            String[] thisSiteStringList = siteString.split(" ");
            Site thisSite = new Site(thisSiteStringList[0], thisSiteStringList[1]);
            siteList.add(thisSite);
        }
    }

    public List<Site> getSiteList() {
        return siteList;
    }

    public String[] getSiteUrlArray(){
        String[] siteUrlArray = new String[siteList.size()];
        for (int i = 0; i < siteList.size(); i++) {
            siteUrlArray[i] = siteList.get(i).getUrl();
        }
        return siteUrlArray;
    }

    public String domain2name(String domain){
        for(Site site: siteList){
            if(site.getDomain().equals(domain)) return site.getName();
        }
        return "未知来源";
    }

    public String domain2type(String domain){
        for(Site site: siteList){
            if(site.getDomain().equals(domain)) return site.getType();
        }
        return "未知类型";
    }

    public String url2name(String url){
        return domain2name(UrlUtils.getDomain(url));
    }

    public String url2type(String url){
        return domain2type(UrlUtils.getDomain(url));
    }
}
