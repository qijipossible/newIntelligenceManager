package spider.pipeline;

import business.Parse;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class ParsePipeline implements Pipeline {

    public void process(ResultItems resultItems, Task task) {
        String url = resultItems.get("url");

        if(url != null) resultItems.put("source", Parse.url2source(url));
        if(url != null) resultItems.put("sourceType", Parse.url2sourceType(url));
    }
}
