<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>舆论监测系统</title>
    <link rel="stylesheet" href="css/style.default.css" type="text/css"/>
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="./js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
    <script type="text/javascript" src="./js/plugins/jquery.cookie.js"></script>
    <script type="text/javascript"
            src="./js/plugins/tinymce/jquery.tinymce.js"></script>
    <script type="text/javascript" src="./js/custom/general.js"></script>
    <script type="text/javascript" src="./js/custom/editor.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"/>


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
            <span class="slogan">科技部</span> <br clear="all"/>
        </div>
        <!--left-->
        <div class="right"></div>
    </div>
    <!--topheader-->
    <div class="header">
        <div style="float: left; margin-top: 2%; margin-left: 1%">

            <c:choose>
                <c:when test="${isCrawling==1}">
                    <div
                            style="background-color: white; width: 600px; height: 34px; float: left;margin-right:4px">
                        <p id="crawlingState"></p>
                    </div>
                    <button class="stdbtn btn_blue">正在搜索</button>
                </c:when>
                <c:otherwise>
                    <form class="stdform" action="SearchServlet" method="post"
                          style="float: left">
                        <input type="hidden" name="method" value="startSearch"/> <input
                            type="text" name="key" class="smallinput"
                            style="height: 34px; width: 600px" value="${key }">
                        <button class="stdbtn btn_blue">全局搜索</button>
                    </form>
                </c:otherwise>
            </c:choose>
            <a href="<c:url value='SearchServlet?method=stopSearch'/>">
                <button
                        class="stdbtn btn_blue">停止搜索
                </button>
            </a>

        </div>
        <div style="float: right">
            <ul class="headermenu">
                <li><a href="<c:url value='ReportServlet?method=generateReport'/>"><span class="icon icon-chart"></span>显示结果</a>
                </li>
            </ul>
        </div>
    </div>
    <c:if test="${isFinished ==1}">

        <div id="content" class="contentwrapper" style="padding-top: 1px">
            <div class="pageheader">
                <ul class="hornav" style="margin-top: 0; padding-top: 0;">
                    <li class="current"><a href="#t_1">全体数据</a></li>
                        <%--<li><a href="#t_2" id="tab2">全体数据</a></li>--%>
                </ul>
            </div>
            <div id="t_1" class="subcontent"
                 style="display: block; overflow-x: hidden">

                <table class="stdtable">
                        <%--<tr>--%>
                        <%--<th width="7%">标题</th>--%>
                        <%--<th width="10%">标题</th>--%>
                        <%--<th width="15%">内容 </th>--%>
                        <%--<th width="5%">时间</th>--%>
                        <%--<th width="3%">来源</th>--%>
                        <%--<th width="3%">内容类别</th>--%>
                        <%--<th width ="3%">来源类别</th>--%>
                        <%--<th width="6%">关键词</th>--%>
                        <%--<th width="15%">摘要</th>--%>
                        <%--&lt;%&ndash;<th width="2%">其他</th>&ndash;%&gt;--%>
                        <%--</tr>--%>
                    <c:forEach items="${recordList}" var="record" varStatus="status">
                        <tr>
                            <td>
                                <a  data-toggle="modal" data-target="#myModal${status.count}" style="font-size: 150%;color:#00F">
                                        ${record.title}
                                </a>
                                <!-- 模态框（Modal） -->
                                <div class="modal fade" id="myModal${status.count}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                    &times;
                                                </button>
                                                <h4 class="modal-title" id="myModalLabel">
                                                    内容
                                                </h4>
                                            </div>
                                            <div class="modal-body">
                                                    ${record.content}
                                            </div>

                                        </div><!-- /.modal-content -->
                                    </div><!-- /.modal -->
                                </div>

                                <div style="font-size: 70%;color: grey">${record.date}</div>
                                <a style="font-size: 100%;color: #0f5955" href="${record.url}">${record.url}</a>
                                <div style="font-size: 120%;">内容类别：${record.contentType}</div>
                                <div style="font-size: 120%;">来源：${record.source}</div>
                                <div style="font-size: 120%;">来源类别：${record.sourceType}</div>
                                <div style="font-size: 120%;">关键词：${record.keyword}</div>
                                <div style="font-size: 120%;">摘要：${record.summary}</div>



                            </td>
                                <%--<td><a href = "${record.url}">${record.url}</a></td>--%>
                                <%--<td>${record.title}</td>--%>
                                <%--<td>${record.content}</td>--%>
                                <%--<td>${record.date}</td>--%>
                                <%--<td>${record.source}</td>--%>
                                <%--<td>${record.contentType}</td>--%>
                                <%--<td>${record.sourceType}</td>--%>
                                <%--<td>${record.keyword}</td>--%>
                                <%--<td>${record.summary}</td>--%>
                                <%--<td>${record.other}</td>--%>
                        </tr>


                    </c:forEach>
                </table>
                    <%--<iframe src="<c:url value='report.jsp'/>" name="report"--%>
                    <%--width="100%" height="10000px"></iframe>--%>
            </div>
                <%--<div id="t_2" class="subcontent"--%>
                <%--style="display: none; overflow-x: hidden;"></div>--%>
        </div>
    </c:if>
    <c:if test="${isCrawling ==1}">
        <div id="crawlingState" class="contentwrapper" style="margin-left: 0">
        </div>
    </c:if>
    <!--leftmenu-->

</div>
<script type="text/javascript">


    function getCrawlingState() {

        jQuery.ajax({
            url: "CrawlServlet",
            context: document.body,
            success: function (data) {
                document.getElementById("crawlingState").innerHTML = data;
                // jQuery("#crawlingState").html(data);
            }
        });
    }

    if ("${isCrawling}" == 1) {
        setInterval("getCrawlingState()", "1000");
    }
</script>
</body>
</html>