<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>舆论监测系统</title>
<link rel="stylesheet" href="css/style.default.css" type="text/css" />
<script type="text/javascript" src="./js/plugins/jquery-1.7.min.js"></script>
<script type="text/javascript"
	src="./js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="./js/plugins/jquery.cookie.js"></script>
<script type="text/javascript"
	src="./js/plugins/tinymce/jquery.tinymce.js"></script>
<script type="text/javascript" src="./js/custom/general.js"></script>
<script type="text/javascript" src="./js/custom/editor.js"></script>

<!--[if IE 9]>
    <link rel="stylesheet" media="screen" href="css/style.ie9.css"/>
<![endif]-->
<!--[if IE 8]>
    <link rel="stylesheet" media="screen" href="css/style.ie8.css"/>
<![endif]-->
<!--[if lt IE 9]>
	<script src="js/plugins/css3-mediaqueries.js"></script>
<![endif]-->
<style type="text/css">
.keyword {
	background-color: yellow;
	font-weight: bold
}

pre {
	white-space: pre-wrap;
	word-wrap: break-word;
}
</style>
</head>
<body>
	<div class="bodywrapper">
		<div class="topheader">
			<div class="left">
				<h1 class="logo">
					舆论<span>检测系统</span>
				</h1>
				<span class="slogan">科技部</span> <br clear="all" />
			</div>
			<!--left-->
			<div class="right"></div>
		</div>
		<!--topheader-->
		<div class="header">
			<div style="float: left; margin-top: 2%; margin-left: 1%">
				<c:if test="${isCrawling==0}">
					<form class="stdform" action="SearchServlet" method="post"
						style="float: left">
						<input type="hidden" name="method" value="startSearch" /> <input
							type="text" name="key" class="smallinput"
							style="height: 18px; width: 600px" value="${key }">
							<button class="stdbtn btn_blue">全局搜索</button>
					</form>
				</c:if>
				<c:if test="${isCrawling==1}">
					<div
						style="background-color: white; width: 600px; height: 34px; float: left;margin-right:4px">
						<p id="crawlingState"></p>
					</div>
					<button class="stdbtn btn_blue">正在搜索</button>

				</c:if>
				<a href="<c:url value='SearchServlet?method=stopSearch'/>"><button
						class="stdbtn btn_blue">停止搜索</button></a>

			</div>
			<div style="float: right">
				<ul class="headermenu">
					<li><a href="<c:url value='ReportServlet?method=generateReport'/>"><span class="icon icon-chart"></span>生成报告</a></li>
				</ul>
			</div>
		</div>
		<c:if test="${isFinished ==1}">

			<div id="content" class="contentwrapper" style="padding-top: 1px">
				<div class="pageheader">
					<ul class="hornav" style="margin-top: 0; padding-top: 0;">
						<li class="current"><a href="#t_1">舆情报告</a></li>
						<li><a href="#t_2" id="tab2">全体数据</a></a></li>
					</ul>
				</div>
				<div id="t_1" class="subcontent"
					style="display: block; overflow-x: hidden">
					<iframe src="<c:url value='report.jsp'/>" name="report"
						width="100%" height="10000px"></iframe>
				</div>
				<div id="t_2" class="subcontent"
					style="display: none; overflow-x: hidden;"></div>
			</div>
		</c:if>
		<c:if test="${isCrawling ==1}">
			<div id="crawlingState" class="contentwrapper" style="margin-left: 0">
			</div>
		</c:if>
		<!--leftmenu-->


	</div>
	<script type="text/javascript">
		function createXMLHttpRequest() {
			try {
				return new XMLHttpRequest();//大多数浏览器
			} catch (e) {
				try {
					return ActvieXObject("Msxml2.XMLHTTP");//IE6.0
				} catch (e) {
					try {
						return ActvieXObject("Microsoft.XMLHTTP");//IE5.5及更早版本	
					} catch (e) {
						alert("哥们儿，您用的是什么浏览器啊？");
						throw e;
					}
				}
			}
		}

		function getCrawlingState() {
			var xmlHttp = createXMLHttpRequest();
			xmlHttp.onreadystatechange = function() {
				if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
					document.getElementById("crawlingState").innerHTML = xmlHttp.responseText;

				}
			};
			xmlHttp.open("GET", "<c:url value='/CrawlServlet'/>", false);
			xmlHttp.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xmlHttp.send(null);
		}

		if ("${isCrawling}" == 1) {
			setInterval("getCrawlingState()", "1000");
		}
	</script>
</body>
</html>