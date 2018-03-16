<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<c:choose>
	<c:when test="${requestScope.reqResult.success==false}">
		<font color="red"><c:out
				value="${requestScope.reqResult.message}" /></font>
	</c:when>
	<c:otherwise>
		<c:if test="${empty requestScope.reqResult.result}">
			<span>还未添加任何归档</span>
		</c:if>
		<c:forEach items="${requestScope.reqResult.result}" var="result">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="" style="outerline-style:none"><c:out value="${result.title}"/></a>
					</h4>
				</div>
				<div class="panel-collapse">
					<div class="panel-body">
						<pre style="border:none;"><c:out value="${result.detail}"/></pre>
					</div>
				</div>
			</div>
		</c:forEach>
	</c:otherwise>
</c:choose>


<!-- 
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" 
                href="#collapseOne">
                点击我进行展开，再次点击我进行折叠。第 1 部分
                </a>
            </h4>
        </div>
        <div id="collapseOne" class="panel-collapse collapse in">
            <div class="panel-body">
                Nihil anim keffiyeh helvetica, craft beer labore wes anderson 
                cred nesciunt sapiente ea proident. Ad vegan excepteur butcher 
                vice lomo.
            </div>
        </div>
    </div>
    
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" 
                href="">
                点击我进行展开，再次点击我进行折叠。第 2 部分
            </a>
            </h4>
        </div>
        <div id="collapseTwo" class="panel-collapse">
        <div class="panel-body">
            Nihil anim keffiyeh helvetica, craft beer labore wes anderson 
            cred nesciunt sapiente ea proident. Ad vegan excepteur butcher 
            vice lomo.
        </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" 
                href="#collapseThree">
                点击我进行展开，再次点击我进行折叠。第 3 部分
                </a>
            </h4>
        </div>
        <div id="collapseThree" class="panel-collapse collapse">
            <div class="panel-body">
                Nihil anim keffiyeh helvetica, craft beer labore wes anderson 
                cred nesciunt sapiente ea proident. Ad vegan excepteur butcher 
                vice lomo.
            </div>
        </div>
    </div>-->
