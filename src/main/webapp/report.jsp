<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>top</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>浙江法院评估报告</title>
<link href="./css/bootstrap.min.css" rel="stylesheet">
<script src="./js/bootstrap.min.js"></script>
<script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>

<style type="text/css">
body {
	height: 50px;
	padding: 70px;
}

li {
	font-family: 微软雅黑;
	font-size: 25px;
	line-height: 200%;
}

p {
	font-family: 微软雅黑;
	font-size: 25px;
	text-indent: 2em;
	line-height: 200%;
}

table {
	font-family: 微软雅黑;
	font-size: 25px;
}

h1#first {
	font-size: 45px;
	font-weight: 5000;
}
</style>
<body>
	<div class="container">
		<div class="page-header">
			<h1 id="first" align="center">
				基于网络舆情的浙江法院评估报告 <small>2017-01-09 </small>
			</h1>
		</div>
	</div>
	<p class="bg-success">本次报告通过全网络数据采集，采集了127个网站，分析了2015篇文章，16条评论，通过分词、聚类、查重、摘要等手段和方法，对各类数据进行分析形成图表，有关情况报告如下。</p>
	<hr />
	<div id="first">
		<h1 align="left">一、主体分析</h1>
		<h2>1.关注度分析</h2>
		<p>
			关注量是网络上涉及浙江法院的文章（包括公文）或评论的数量。自2002年开始，网络上出现了的有关信息，之后关注量快速上升。2012年达到了峰值，关注量达389次，随后关注量呈现随步下降的趋势；其次,2016年的关注量达385次；再次，2014年的关注量达354次。
		</p>
		<div class="container">
			<div class="row">
				<div id="main2" style="width: 100%; height: 400px;"></div>
			</div>
		</div>

		<h2>2.舆论统计</h2>
		<p>
			态度指数值是用来衡量社会态度波动状况的综合指数，分为-5到+5共11个等级，越靠近-5态度越负面，越靠近+5态度越正面。百分之13.244707的态度指数是1；百分之12.801576的态度指数是0；百分之11.619892的态度指数是2。
		</p>
		<div class="container">
			<div class="row">
				<div id="main3" style="width: 100%; height: 400px;"></div>
			</div>
		</div>

		<h2>3.关注的主体</h2>
		<p>
			将关注的主题分为政府官网，新闻媒体，社会公众，对采集的数据进行分析，其中政府官网发表文章或评论20篇，占总数量的百分之0.98473656；新闻媒体发表文章或评论（包含公文）1995篇，占总数量的百分之98.22747；社会公众发表文章或评论（包含公文）16篇，占总数量的百分之0.7877893。
		</p>
		<div class="container">
			<div class="row">
				<div id="main1" style="width: 100%; height: 400px;"></div>
			</div>
		</div>
	</div>

	<h3>（1）政府官网的态度指数</h3>
	<p>
		对政府官网发表的文章（包含评论）和评论数据进行分析，百分之25.0的态度指数是5；百分之20.0的态度指数是0；百分之10.0的态度指数是-1。
	</p>
	<div class="container">
		<div class="row">
			<div id="main31" style="width: 100%; height: 400px;"></div>
		</div>
	</div>

	<h3>（2）新闻媒体的舆情指数</h3>
	<p>
		对新闻媒体发表的文章（包含评论）和评论数据进行分析，百分之13.333333的态度指数是1；百分之12.83208的态度指数是0；百分之11.729323的态度指数是2。
	</p>
	<div class="container">
		<div class="row">
			<div id="main32" style="width: 100%; height: 400px;"></div>
		</div>
	</div>

	<h3>（3）公众的关注指数</h3>
	<p>
		对公众发表的文章（包含评论）和评论数据进行分析，百分之50.0的态度指数是-5；百分之12.5的态度指数是-2；百分之6.25的态度指数是-4。
	</p>
	<div class="container">
		<div class="row">
			<div id="main33" style="width: 100%; height: 400px;"></div>
		</div>
	</div>
	<hr />

	<div id="Second">
		<br />
		<h1 align="left">二、主题分析（主题词）</h1>
		<p>通过分词计算，政府、媒体和社会公众发表公文、文章和评论中的关键词主要如下：</p>
		<ul>
			<li>全网：法院,浙江,司法,案件,工作,法律,
				&middot,人民法院,法官,执行,浙江省,进行,高院,拍卖,问题,审判,人员,纠纷</li>
			<li>政府：法院,被告,人民法院,原告,有限公司,执行,法庭,纠纷,司法,案件,信息,浙江,承办,宁波市,拍卖,宁波,机构,审判,开庭,文书</li>
			<li>媒体：法院,浙江,司法,工作,
				&middot,案件,法官,浙江省,法律,人民法院,执行,拍卖,高院,进行,问题,企业,诉讼,审判</li>
			<li>公众：浙江,大学,法律,研究,浙江省,法学,回答,中小企业,教授,我院,法院,司法,发展,国际,法学院,问题,工作,进行,博士</li>
			<li>明显峰值年度以及主题词：2012,法院,浙江,
				&middot,司法,工作,法律,案件,拍卖,高院,法官,人民法院,浙江省,执行,进行,社会,问题,法治,企业,民间,借贷</li>
		</ul>
		<p>除去一些没有明显含义的关键词，各类主题词如下表所示。</p>
		<br>
		<table class="table table-striped">
			<caption align="center">表1：各种类型的主题词</caption>
			<thead>
				<tr>
					<th></th>
					<th>第一关注主题</th>
					<th>第二关注主题</th>
					<th>第三关注主题</th>
					<th>第四关注主题</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>政府官网</th>
					<td>法院</td>
					<td>被告</td>
					<td>原告</td>
					<td>有限公司</td>
				</tr>
				<tr>
					<th>新闻媒体</th>
					<td>法院</td>
					<td>浙江</td>
					<td>司法</td>
					<td>工作</td>
				</tr>
				<tr>
					<th>公众</th>
					<td>浙江</td>
					<td>大学</td>
					<td>法律</td>
					<td>研究</td>
				</tr>
				<tr>
					<th>明显峰值</th>
					<td>法院</td>
					<td>浙江</td>
					<td>&middot</td>
					<td>司法</td>
				</tr>
			</tbody>
		</table>

	</div>
	<hr />
	<div id="Third">
		<br />
		<h1 align="left">三、态度（情感）分析</h1>
		<br />
		<p>
			舆论指数通过对网络中各种类型媒体所发布的信息进行独立的第三方观察，形成量化统计和定性分析，并结合算法推导、归纳总结而最终形成的一套网络舆论指数体系。其中算法主要是靠对句子的关键词分析得出每句话的舆论指数，再得到每篇文章的综合指数。本报告根据网络采集数据，通过舆情分析算法，对网络整体、政府官网、新闻媒体以及公众的舆论指数进行分析，具体指数如图所示。
		</p>
		<div class="container">
			<div class="row">
				<div id="main4" style="width: 1100px; height: 500px;"></div>
				<div class="col-md-6">
					<div id="main5" style="width: 100%; height: 400px;"></div>
				</div>
				<div class="col-md-6">
					<div id="main6" style="width: 100%; height: 400px;"></div>
				</div>
				<div class="col-md-6">
					<div id="main7" style="width: 100%; height: 400px;"></div>
				</div>
			</div>
		</div>
	</div>
	<p>根据统计结果，全网整体的舆论指数:0.83928823，整体评价偏向于正面评价；政府的态度指数最高，综合为正面评价，态度指数为1.5855664；公众的态度指数最低，综合为负面评价，态度指数为-2.7484574；媒体的态度指数居中，综合评价为正面评价，态度指数为0.8605784；
	</p>

	<hr />
	<div id="Forth">
		<br />
		<h1 align="left">四、热点分析</h1>
		<h2>1. 热点主题</h2>
		<p>
			综合网络数据采集，在全网范围内，总体的热点主题中负面评价较多的词为：浙江,法院,蔡锦聪,人民法院,被害人,网上,投资,被告人,集资,进行,起诉,海盐县,浙江省,信息,拍卖,淘宝,电子商务,部分,执行；正面评价较多的词为：法院,浙江,司法,工作,人民法院,浙江省,案件,服务,高院,全省,法官,信息,拍卖,建设,审判,执行,全国,公开,进行,知识产权；
		</p>
		<h2>2. 集中度较高的负面观点和集中度较高的正面观点</h2>
		<p>根据采集的数据，对评价为负面的信息进行统计分析，其中负面评价主要集中在：-1级</p>
		<p>其主要的主题词为：浙江,法院,蔡锦聪,人民法院,被害人,网上,投资,被告人,集资,进行,起诉,海盐县,浙江省,信息,拍卖,淘宝,电子商务,部分,执行</p>
		<p>负面评价的主要观点为：浙江法院2005年受理一审建设工程施工合同纠纷案件2682件
			浙江法院2005年受理一审建设工程施工合同纠纷案件2682件 浙江法院实施网络司法拍卖 目前法院审理的建设工程施工合同纠纷案件中
			浙江全省法院系统在审理建设工程施工合同纠纷案件时 浙江法院司法拍卖首次在淘宝网上举行 浙江法院司法拍卖首次在淘宝网上举行
			浙江法院首次试水网络司法拍卖后 浙江法院在淘宝网上举行的第二次网络司法拍卖 在浙江法院开设的司法拍卖 ；</p>
		<p>对评价为正面的信息进行统计分析，其中正面评价主要集中在：1级</p>
		<p>其主要的主题词为：法院,浙江,司法,工作,人民法院,浙江省,案件,服务,高院,全省,法官,信息,拍卖,建设,审判,执行,全国,公开,进行,知识产权</p>
		<p>正面评价的主要观点为：从浙江法院开始的网络司法拍卖 从浙江法院开始的网络司法拍卖 2009年浙江法院司法拍卖的佣金是2.4亿元
			引争议・浙江法院司法拍卖亮相淘宝合法性遭质疑・浙江19家法院将试点网上拍卖涉诉资产30万人围观・浙江法院涉诉资产将在淘宝进行网上拍卖・浙江法院拟将司法拍卖搬上网络称期更公开透明
			浙江法院将执行标的物上淘宝网进行公开司法拍卖 浙江法院在淘宝网上举行的第二次网络司法拍卖 浙江法院首次在淘宝网上举行网络司法拍卖
			浙江法院司法拍卖首次在淘宝网上举行 浙江法院已经有七八成的司法拍卖上网解决了 187家法院上淘宝司法拍卖 ；</p>

	</div>
</body>

</html>
</body>
</html>