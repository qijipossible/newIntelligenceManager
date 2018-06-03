package UI.servlet;

//import spider.SpiderManager;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Servlet implementation class CrawlServlet
 */
@WebServlet("/CrawlServlet")
public class CrawlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	private SpiderManager spiderManager = SpiderManager.getInstance();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");


//		Map<String, Integer> countMap = spiderManager.getPageCountMap();

		//生成测试用的countMap，需要改成真实数据
		Map<String, Integer> countMap = new HashMap<String, Integer>();
		Random random = new Random();
		countMap.put("success", random.nextInt());
		countMap.put("left", random.nextInt());


		int downloaded = countMap.get("success");
		int left = countMap.get("left");

		response.getWriter().write("已下载了"+downloaded+"个，还剩"+left+"个.");
		System.out.println("已下载了"+downloaded+"个，还剩"+left+"个.");
	}

}
