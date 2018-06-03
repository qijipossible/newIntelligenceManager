package UI.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.logging.SimpleFormatter;

import cn.itcast.servlet.BaseServlet;
import database.entity.Record;

//import cn.itcast.servlet.BaseServlet;
//import service.ControlService;
//import service.DataService;

/**
 * Servlet implementation class ReportServlet
 */
@WebServlet("/ReportServlet")
public class ReportServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

//	private DataService dataService = DataService.getInstance();
	public String generateReport(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().setAttribute("isFinished", 1);

		try {
			//这部分为自己生成的一个recordList，实际运行需要修改
			ArrayList<Record> recordList = new ArrayList<Record>();
			SimpleDateFormat format1 =  new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = format1.parse("2017-01-01");
			Record record1 = new Record("https://cn.nytimes.com/china/20120716/cc16wangqiang/", "从《人民的名义》一窥中国的“公车改革制度”！_搜狐社会_搜狐网", "《关于全面推进公务用车制度改革的指导意见》和《中央和国家机关公务用车制度改革方案》今天下发：1、取消一般公务用车；2、普通公务出行社会化", date1, "搜狐网", "社会", "政府", "公车改革，公务用车，社会化", " 现实世界的黑色幽默已经令公众难以在公车改革上与政府达成信任与共识。公车改革过程中所演绎出来的场景恰如中国整体改革进程中的一个缩影", "哈哈哈");
			Record record2 = new Record("www.google.com", "什么小东西", "fdsf", date1, "热敏网", "string", "政府", "bang,不好", "dshaide", "没啦");
			recordList.add(record1);
			recordList.add(record2);



			request.getSession().setAttribute("recordList",recordList);

		} catch (Exception e) {              // Insert this block.
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		return "index.jsp";
	}
	
	
}