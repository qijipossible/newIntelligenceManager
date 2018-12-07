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
import database.entity.Cluster;
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
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = format1.parse("2017-01-01");
			Record record1 = new Record("https://cn.nytimes.com/china/20120716/cc16wangqiang/", "从《人民的名义》一窥中国的“公车改革制度”！_搜狐社会_搜狐网", "《关于全面推进公务用车制度改革的指导意见》和《中央和国家机关公务用车制度改革方案》今天下发：1、取消一般公务用车；2、普通公务出行社会化", date1, "搜狐网", "社会", "政府", "公车改革，公务用车，社会化", " 现实世界的黑色幽默已经令公众难以在公车改革上与政府达成信任与共识。公车改革过程中所演绎出来的场景恰如中国整体改革进程中的一个缩影", "哈哈哈");
			Record record2 = new Record("http://www.sastind.gov.cn/n112/n117/c6801754/content.html", "四川省军民融合产业发展专项资金项目验收会召开", "5月28日，四川省国防科工办在成都召开省级军民融合产业发展专项资金项目验收工作会，总结了2017年验收工作，安排部署今年重点工作。 　　会上，四川省国防科工办就《四川省军民融合产业发展专项资金管理办法》、《四川省省级军民结合产业发展专项资金项目验收实施细则》、项目验收申请报告及附表的编制、项目财务决算和审计编制等内容进行讲解和培训，并进行了现场互动、答疑。绵阳市国防科工办和5719厂分别代表市级国防科技工业管理部门和项目单位作经验交流发言。 　　会议指出，军民融合产业发展专项资金对带动四川省军民融合产业化、技术创新和平台共享发挥了积极作用，一大批优秀项目已经成功产业化并产生了巨大的经济效益，成为了军民融合典范。 　　会议强调，各单位要充分认识做好专项资金项目验收的重要意义，使用好、管理好专项资金，使之发挥最大效益并确保资金使用的合理、合规和合法。 主办单位：国家国防科技工业局地址：北京市海淀区阜成路甲8号邮编：100048承办单位：国家国防科技工业局新闻宣传中心信息报送邮箱：webmaster@sastind.gov.cn国家国防科技工业局版权所有京ICP备11007804号", date1, "鍥介槻绉戝伐灞�", "", "官方网站", "[项目, 专项资金, 国家国防科技工业局, 单位]", "[四川省国防科工办就《四川省军民融合产业发展专项资金管理办法》、《四川省省级军民结合产业发展专项资金项目验收实施细则》、项目验收申请报告及附表的编制、项目财务决算和审计编制等内容进行讲解和培训, 四川省国防科工办在成都召开省级军民融合产业发展专项资金项目验收工作会, 军民融合产业发展专项资金对带动四川省军民融合产业化、技术创新和平台共享发挥了积极作用]\t", "没啦");
			recordList.add(record2);
			recordList.add(record1);

			ArrayList<Cluster> clusterList = new ArrayList<Cluster>();

			Cluster cluster = new Cluster("第一个abstract", 1, recordList, "可爱，美丽，喜欢，走开");

			Cluster cluster2 = new Cluster("第二个abstract", 2, recordList, "搜狐，阿里，腾讯，偷偷");

			clusterList.add(cluster);
			clusterList.add(cluster2);


			request.getSession().setAttribute("clusterList", clusterList);

		} catch (Exception e) {              // Insert this block.
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return "index.jsp";
	}


}