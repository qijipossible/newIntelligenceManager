package UI.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CrawlServlet
 */
@WebServlet("/CrawlServlet")
public class CrawlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DataService dataService = DataService.getInstance();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		CrawlingState crawlingState = dataService.getCrawlingState();
		int scanned = crawlingState.getScanned();
		int downloaded = crawlingState.getDowloaded();
		int left =crawlingState.getLeft();
		response.getWriter().write("已爬取了"+scanned+"个网页，下载了"+downloaded+"个，还剩"+left+"个.");
		
	}

}
