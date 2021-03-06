<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<title>空调</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/device/airConditioningControl.css"/>
<link rel="stylesheet" href="js/bootstrap-4.0.0/css/bootstrap.min.css" />
<script src="js/device/jquery.min.js"></script>
<script src="js/bootstrap-4.0.0/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
</head>
<body>
	<div class="colorBlock3">
		<!-- 隐藏input接受参数 -->
		<input id="deviceInformation" type="hidden" value='${deviceInformation}' /> 
		<input id="GROUP_NUM" type="hidden" />
		<!-- 重要参数请勿动 end -->
		<div class="displayArea">
			<div class="bacor">
				<div class="outside">
					<div class="inside">
						<div class="content">
							<div class="tips">设定温度(°C)</div>
							<div class="num">
								<span id="ADR_TMP">28</span>
							</div>
							<div class="shownum">
								室内温度:<span id="ROOM_TMP">23</span>°C
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="model">
				<div class="capsule">
					<div class="stase" role="button" id="open" data-status="00"
						data-control=true>开启</div>
					<div class="stase" role="button" id="close" data-status="01"
						data-control=false>关闭</div>
				</div>
				<div class="model-item">
					<div class="item">
						<span id="state"> <span data-status="00" data-control=true
							class="hide">开启</span> <span data-status="01" data-control=false
							class="hide">关闭</span>
						</span> <span>设备状态</span>
					</div>
					<div class="item">
						<span id="mode"> <span data-status="00" data-control="0"
							class="hide">自动</span> <span data-status="01" data-control="1"
							class="hide">制冷</span> <span data-status="02" data-control="2"
							class="hide">除湿</span> <span data-status="03" data-control="3"
							class="hide">送风</span> <span data-status="04" data-control="4"
							class="hide">制热</span>
						</span> <span>模式</span>
					</div>
					<div class="item">
						<span id="windSpeed"> <span data-status="00"
							data-control="0" class="hide">自动</span> <span data-status="01"
							data-control="1" class="hide">低</span> <span data-status="02"
							data-control="2" class="hide">中</span> <span data-status="03"
							data-control="3" class="hide">高</span>
						</span> <span>风速</span>
					</div>
					<div class="item">
						<span id="sweepWind"> <span data-status="00"
							data-control="6" class="hide">自动</span> <span data-status="01"
							data-control="7" class="hide">手动</span>
						</span> <span>扫风</span>
					</div>
				</div>
			</div>
			<div class="Content_under">
				<div class="container">
					<div class="row">
						<div class="col-4 width2">
							<!--温度加-->
							<p>
								<img src="images/airConditioning/7.png" id="reduce" />
							</p>
						</div>
						<div class="col-4 width2">
							<!--显示温度-->
							<h4 id="temperature">24</h4>
							<p>设置温度（℃）</p>
						</div>
						<div class="col-4 width2">
							<!--温度减-->
							<p>
								<img src="images/airConditioning/6.png" id="add" />
							</p>
						</div>

					</div>
				</div>
				<div class="container">
					<div class="row">
						<div class="col-4 width2">
							<!--模式-->
							<img src="images/airConditioning/1.png" data-toggle="modal"
								data-target="#myModal_1" />
							<p>模式</p>
						</div>
						<div class="col-4 width2">
							<!--风速-->
							<img src="images/airConditioning/4.png" data-toggle="modal"
								data-target="#myModal_2" />
							<p>风速</p>
						</div>

						<div class="col-4 width2">
							<!--扫风-->
							<img src="images/airConditioning/5.png" data-toggle="modal"
								data-target="#myModal_3" />
							<p>扫风</p>
						</div>
					</div>
				</div>

				<!-- 模态框 -->
				<div class="modal fade" id="myModal_1">
					<div class="modal-dialog">
						<div class="modal-content">

							<!-- 模态框头部 -->
							<div class="modal-header">
								<h4 class="modal-title">空调模式</h4>
								<!--<button type="button" class="close" data-dismiss="modal">&times;</button>-->
							</div>

							<!-- 模态框主体 -->
							<div class="modal-body">
								<p class="mode-automatic" data-dismiss="modal" data-status="00"
									data-control="0">
									<img src="images/airConditioning/17.png" /> <span>自动</span>
								</p>
								<p class="mode-cold" data-dismiss="modal" data-status="01"
									data-control="1">
									<img src="images/airConditioning/15.png" /> <span>制冷</span>
								</p>
								<p class="mode-heat" data-dismiss="modal" data-status="04"
									data-control="4">
									<img src="images/airConditioning/16.png" /> <span>制热</span>
								</p>
								<p class="mode-dehumidif" data-dismiss="modal" data-status="02"
									data-control="2">
									<img src="images/airConditioning/9.png" /> <span>除湿</span>
								</p>
								<p class="mode-airSupply" data-dismiss="modal" data-status="03"
									data-control="3">
									<img src="images/airConditioning/14.png" /> <span>送风</span>
								</p>
							</div>

						</div>
					</div>
				</div>
				<!-- 模态框 -->
				<div class="modal fade" id="myModal_2">
					<div class="modal-dialog">
						<div class="modal-content">

							<!-- 模态框头部 -->
							<div class="modal-header">
								<h4 class="modal-title">空调风速</h4>
								<!--<button type="button" class="close" data-dismiss="modal">&times;</button>-->
							</div>

							<!-- 模态框主体 -->
							<div class="modal-body">
								<p class="windSpeed-automatic" data-dismiss="modal"
									data-status="00" data-control="0">
									<img src="images/airConditioning/15.png" /> <span>自动</span>
								</p>
								<p class="windSpeed-high" data-dismiss="modal" data-status="03"
									data-control="3">
									<img src="images/airConditioning/12.png" /> <span>高</span>
								</p>
								<p class="windSpeed-middle" data-dismiss="modal"
									data-status="02" data-control="2">
									<img src="images/airConditioning/11.png" /> <span>中</span>
								</p>
								<p class="windSpeed-low" data-dismiss="modal" data-status="01"
									data-control="1">
									<img src="images/airConditioning/10.png" /> <span>低</span>
								</p>
							</div>

						</div>
					</div>
				</div>
				<!-- 模态框 -->
				<div class="modal fade" id="myModal_3">
					<div class="modal-dialog">
						<div class="modal-content">

							<!-- 模态框头部 -->
							<div class="modal-header">
								<h4 class="modal-title" style="text-align: center;">空调扫风</h4>
								<!--<button type="button" class="close" data-dismiss="modal">&times;</button>-->
							</div>

							<!-- 模态框主体 -->
							<div class="modal-body">
								<p class="sweepWind-automatic" data-dismiss="modal"
									data-status="00" data-control="6">
									<img src="images/airConditioning/17.png" /> <span>自动</span>
								</p>
								<p class="sweepWind-Manual" data-dismiss="modal"
									data-status="01" data-control="7">
									<img src="images/airConditioning/13.png" /> <span>手动</span>
								</p>
							</div>

						</div>
					</div>
				</div>

				<div class="container">
					<div class="row">
						<div class="col-4 width2">
							<!--费用-->
							<img src="images/airConditioning/3.png" />
							<p>费用</p>
						</div>
						<div class="col-4 width2">
							<!--匹配-->
							<img src="images/airConditioning/8.png" />
							<p>匹配</p>
						</div>
						<div class="col-4 width2">
							<!--定时-->
							<img src="images/airConditioning/2.png" />
							<p>定时</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="http://pic-static.fangzhizun.com/vue/2.2.6/vue.min.js"></script>
<%--	<script src="<%=path%>/js/airConditioningControl.js"></script>--%>
</body>
</html>
