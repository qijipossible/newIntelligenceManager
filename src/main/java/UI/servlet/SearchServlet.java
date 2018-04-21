package UI.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;
import service.ControlService;
import service.DataService;



/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private ControlService controlService = ControlService.getInstance();
	public String startSearch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String key = request.getParameter("key");		
		controlService.startCrawling(key);
		int iscrawling = controlService.isCrawling;
		request.getSession().setAttribute("isCrawling", iscrawling);
		System.out.println("iscrawling");
		return "index.jsp";
	}
	public String stopSearch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		controlService.stopCrawling();
		int iscrawling = controlService.isCrawling;
		request.getSession().setAttribute("isCrawling", iscrawling);
		System.out.println("stopcrawling");
		return "index.jsp";
	}
	
}
