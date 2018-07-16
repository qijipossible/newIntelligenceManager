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

import UI.UIHelper;
import cn.itcast.servlet.BaseServlet;
import database.DatabaseManager;
import database.entity.Record;

/**
 * Servlet implementation class ReportServlet
 */
@WebServlet("/ReportServlet")
public class ReportServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private UIHelper uiHelper = new UIHelper();

	public String generateReport(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().setAttribute("isFinished", 1);

		try {
			//这部分为自己生成的一个recordList，实际运行需要修改
            uiHelper.parseAll();
			List<Record> recordList = uiHelper.getAll();


			request.getSession().setAttribute("recordList",recordList);

		} catch (Exception e) {              // Insert this block.
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		return "index.jsp";
	}
	
	
}