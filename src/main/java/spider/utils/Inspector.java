package spider.utils;

public class Inspector {
    public String mainKeyword = "军民融合";
    public String mainLimit = "四川";

    public boolean isPageQualified(String title, String content) { //XXX inspector有待深度优化
        return title.contains(mainKeyword) && title.contains(mainLimit)
                || content.contains(mainKeyword) && content.contains(mainLimit)
                || content.contains(mainLimit) && content.contains("军民") && content.contains("融合");
    }

    public boolean isUrlQualified(String url) {
        // XXX 可以通过URL来判断是否为内容页
        return !(url.matches("(?i).*big5.*") || url.matches("(?i).*english.*")); //XXX 有没有更好的正则表达式
    }
}
