package spider.utils;

import java.util.ArrayList;
import java.util.List;

public class Inspector {
//    public String mainKeyword = "军民融合";
//    public String mainLimit = "四川";

    private List<String> mustContain = new ArrayList<String>();
    private List<String> canContain = new ArrayList<String>();
    private List<String> notContain = new ArrayList<String>();

    public void addMustContain(List<String> list){
        mustContain = list;
    }
    public void addCanContain(List<String> list){
        canContain = list;
    }
    public void addNotContain(List<String> list){
        notContain = list;
    }

    public boolean isPageQualified(String title, String content) { //XXX inspector有待深度优化
        boolean temp = false;
        for(String str : mustContain){
            if(!(title.contains(str) && content.contains(str)))
                return false;
        }

        for(String str : canContain){
            if(title.contains(str) && content.contains(str)){
                temp = true;
                break;
            }
        }
        if(!temp) return false;

        for(String str : notContain){
            if(title.contains(str) || content.contains(str))
                return false;
        }
        return true;
    }

    public boolean isUrlQualified(String url) {
        // XXX 可以通过URL来判断是否为内容页
        //目的就是过滤所有不符合要求的url 目前过滤繁体和英文
        return !(url.matches("(?i).*big5.*") || url.matches("(?i).*english.*")); //XXX 有没有更好的正则表达式
    }
}
