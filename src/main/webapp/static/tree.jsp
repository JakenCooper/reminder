<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<c:url value="/bootstrap/css/bootstrap.css"/>"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<c:url value="/js/jquerytree/css/style.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/jquery-3.3.1.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/bootstrap/js/bootstrap.js"/>"></script>
<script type="text/javascript">
$(function(){
    $('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
    $('.tree li.parent_li > span').on('click', function (e) {
        var children = $(this).parent('li.parent_li').find(' > ul > li');
        if (children.is(":visible")) {
            children.hide('fast');
            $(this).attr('title', 'Expand this branch').find(' > i').addClass('glyphicon-plus-sign').removeClass('glyphicon-minus-sign');
        } else {
            children.show('fast');
            $(this).attr('title', 'Collapse this branch').find(' > i').addClass('glyphicon-minus-sign').removeClass('glyphicon-plus-sign');
        }
        e.stopPropagation();
    });
});
</script>
<style type="text/css">
	a{
		color:black;
	}
	.ctext{
	
	}
</style>
</head>
<body>
	<div class="tree well" id="rootblock">
		<ul>
			<li>
				<span><i class="glyphicon glyphicon-folder-open"></i> 所有分类</span>&nbsp;&nbsp;&nbsp;<a href="">添加子分类</a>
				<ul>
					<li>
						<a href="#" onclick="alert($(this).find('.ctext').length);">
							<span><i class="glyphicon glyphicon-leaf"></i>
								<span class="ctext" style="border:none;margin:0px;padding:0px;">Child</span>
							</span>
						</a>
						&nbsp;&nbsp;&nbsp;<a href="">添加子分类</a>
					<li>
						<span><i class="glyphicon glyphicon-minus-sign"></i> Child</span>
						&nbsp;&nbsp;&nbsp;<a href="">添加子分类</a>
						<ul>
							<li>
								<a href="#" onclick="alert($('#rootblock').html())">
									<span><i class="glyphicon glyphicon-leaf"></i>
										<span class="ctext" style="border:none;margin:0px;padding:0px;">Child</span>
									</span>
								</a>
								&nbsp;&nbsp;&nbsp;<a href="">添加子分类</a>
							</li>
						</ul>
					</li>
				</ul>
			</li>
		</ul>
	</div>
</body>
</html>