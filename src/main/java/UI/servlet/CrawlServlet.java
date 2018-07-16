package UI.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import UI.UIHelper;
import spider.SpiderManager;

/**
 * Servlet implementation class CrawlServlet
 */
@WebServlet("/CrawlServlet")
public class CrawlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UIHelper uiHelper = new UIHelper();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");


		Map<String, Integer> countMap = uiHelper.getPageCountMap();


		int downloaded = countMap.get("success");
		int left = countMap.get("left");

		response.getWriter().write("已下载了"+downloaded+"个，还剩"+left+"个.");
	}

}
