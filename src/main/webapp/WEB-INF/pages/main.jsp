<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reminder Application</title>
<link href="<c:url value="/bootstrap/css/bootstrap.css"/>"
	rel="stylesheet">
<script type="text/javascript"
	src="<c:url value="/js/jquery-3.3.1.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/bootstrap/js/bootstrap.js"/>"></script>
<style type="text/css">
.maintitle {
	font-size: 30px;
}

.mainblock {
	margin-top: 30px;
}
.mainblock a{
	outline-style:none;
}

</style>

<script type="text/javascript">
	$(function() {
		if (window.history && window.history.pushState) {
			$(window).on('popstate', function() {
				window.history.pushState('forward', null, '#');
				window.history.forward(1);
			});
		}
		window.history.pushState('forward', null, '#'); //在IE中必须得有这两行
		window.history.forward(1);
	})
	function showArticleDetail() {
		//$('#row1').toggleClass("make-wrap");
		//$('#row1').toggleClass("make-wrap-full");
		//alert('#row'+arguments[0]);
		$('#row'+arguments[0]).toggleClass('hidden');
		$('#row'+arguments[0]+'-hidden').toggleClass('hidden');
	}
</script>
</head>
<body background="<c:url value="/img/0008021010421268_b.jpg"/>">

	<nav class="nav navbar-default navbar-static-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header navbar-right">
				<a class="navbar-brand " href="#"><span
					class="text-primary maintitle"> <strong><em>If
								cannot remember,just reminder.</em></strong>
				</span></a>
			</div>
			<p class="navbar-text navbar-right"></p>
		</div>
	</nav>

	<div class="container-fluid">
	<ul id="navTab" class="nav nav-tabs mainblock" style="font-size:30px;">
	    <li class="active">
	        <a href="#alloblock" data-toggle="tab">
	        		<img src="/reminder/img/nav1.gif"
                 alt="归档工具" style="width:30px;height:30px">
	        		<label> 归档工具</label>
	        </a>
	    </li>
	    <li>
	    		<a href="#workrecordblock" data-toggle="tab">
	    					<img src="/reminder/img/nav2.gif"
                 alt="工作日志" style="width:32px;height:32px">
	    				<label>工作日志</label>
	    		</a>
	    </li>
	</ul>
	<div id="navTabContent" class="tab-content">
	    <div class="tab-pane fade in active" id="alloblock">
	    		<jsp:include page="allo.jsp"></jsp:include>
	    </div>
	    <div class="tab-pane fade" id="workrecordblock">
	    		<jsp:include page="workrecord.jsp"></jsp:include>
	    </div>
	</div>
	</div>

	
</body>
</html>