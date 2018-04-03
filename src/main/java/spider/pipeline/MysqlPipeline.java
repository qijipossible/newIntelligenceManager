package spider.pipeline;

import database.HibernateUtil;
import database.entity.Record;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Date;

public class MysqlPipeline implements Pipeline {

	public synchronized void process(ResultItems resultItems, Task task) {

        String url;
        String title;
        String content;
        Date date;
        String source;
        String contentType;
        String sourceType;
        String keyword;
        String summary;
        String other;

		System.out.print("pipeline processing.\n");

        url = resultItems.get("url");
        title = resultItems.get("title");
        content = resultItems.get("content");
        date = resultItems.get("date");
        source = resultItems.get("source");
        contentType = resultItems.get("contentType");
        sourceType = resultItems.get("sourceType");
        keyword = resultItems.get("keyword");
        summary = resultItems.get("summary");
        other = resultItems.get("other");

        Record record = new Record(url, title, content, date, source, contentType, sourceType, keyword, summary, other);
        HibernateUtil.save(record);
	}
}
