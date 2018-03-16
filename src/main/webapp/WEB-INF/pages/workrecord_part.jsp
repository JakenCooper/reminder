<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<c:choose>
	<c:when test="${empty reqResult.result.recordList}">
		<div>
			<h2 class="text-primary pull-left">
				<strong><em>还未添加任何日志</em></strong>
			</h2>
			<button type="button" class="btn btn-primary btn-lg pull-right"
				data-toggle="modal" data-target="#work_record_add_modal"
				onclick="readyToAddWorkRecord()">
				<span class="glyphicon glyphicon-calendar"></span> 添加日志
			</button>
			<div class="clearfix"></div>
		</div>
	</c:when>
	<c:otherwise>
		<div>
			<h2 class="text-primary pull-left">
				<strong><em>
					<c:out value="${reqResult.result.year}"/>  年  
					<c:out value="${reqResult.result.month}"/>  月
				</em></strong>
				<small> 共<c:out value="${reqResult.result.totalsize}"/>条日志</small>
			</h2>
			<button type="button" class="btn btn-primary btn-lg pull-right"
				data-toggle="modal" data-target="#work_record_add_modal"
				onclick="readyToAddWorkRecord()">
				<span class="glyphicon glyphicon-calendar"></span> 添加新日志
			</button>
			<div class="clearfix"></div>
		</div>
		<hr />
	
		<div class="panel-group" id="workrecordlist">
		
			<c:forEach items="${reqResult.result.recordList}" var="item" varStatus="status">
				<div class="panel panel-default panelstyle">
					<div class="panel-heading bg-primary text-normal"
						style="position: relative">
						<a data-toggle="collapse" data-parent="#workrecordlist"
							href="#workrecordcollapse<c:out value='${status.index}'/>">
							<h3 class="panel-title titlestyle">
								<strong><em><fmt:formatDate value="${item.alloDate}" pattern="yyyy-MM-dd" /></em></strong>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${item.title}"/>
							</h3>
						</a>
						<button type="button" class="btn btn-danger btn-sm delbtn"
							onclick="delWorkRecord('<c:out value="${item.id}"/>')">
							<span class="glyphicon glyphicon-minus"></span> 删除
						</button>
					</div>
					<div id="workrecordcollapse<c:out value='${status.index}'/>" class="panel-collapse collapse <c:if test='${status.index==0}'>in</c:if>">
						<div class="panel-body">
							<pre><c:out value="${item.content}"/></pre>
						</div>
					</div>
				</div>
			
			</c:forEach>
			
			
		</div>
	</c:otherwise>
</c:choose>

