package UI.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import UI.UIHelper;
import cn.itcast.servlet.BaseServlet;
import org.jsoup.Connection;
import spider.SpiderManager;



/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private UIHelper uiHelper = new UIHelper();

	public String startSearch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//这个是传给你的key，是用户输入的搜索参数，得到之后干什么你自己写就行
		String key = request.getParameter("key");
		System.out.println(key);

		uiHelper.startSpider();

		request.getSession().setAttribute("isCrawling", 1);

		return "index.jsp";
	}
	public String stopSearch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	    uiHelper.stopSpider();

		request.getSession().setAttribute("isCrawling", 0);
		System.out.println("stopcrawling");
		return "index.jsp";
	}
	
}
