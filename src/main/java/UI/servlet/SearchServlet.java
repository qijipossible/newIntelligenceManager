package UI.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;
import org.jsoup.Connection;
//import service.ControlService;
//import service.DataService;



/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

//	private ControlService controlService = ControlService.getInstance();
	public String startSearch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//这个是传给你的key，是用户输入的搜索参数，得到之后干什么你自己写就行
		String key = request.getParameter("key");
		System.out.println(key);

		request.getSession().setAttribute("isCrawling", 1);

		return "index.jsp";
	}
	public String stopSearch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		

		request.getSession().setAttribute("isCrawling", 0);
		System.out.println("stopcrawling");
		return "index.jsp";
	}
	
}
