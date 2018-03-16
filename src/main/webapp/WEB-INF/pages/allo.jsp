<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.maintitle {
	font-size: 30px;
}

.mainblock {
	margin-top: 30px;
}

.maincontent {
	font-size: 20px;
}

.singlerow {
	height: 45px;
	font-size: 20px;
}

.singlerowele {
	float: none;
	display: inline-block;
	vertical-align: middle;
	line-height: 45px;
}

.make-wrap {
	width: 350px;
	display: block;
	white-space: nowrap; /*文本段落不换行*/
	overflow: hidden;
	text-overflow: ellipsis; /*当对象内文本溢出时显示省略标记*/
	cursor: pointer;
}

.make-wrap-full {
	max-width: 300px;
	display: block;
	cursor: pointer;
}
</style>
</head>
<body>
<div>
	<div class="mainblock">

		<c:choose>
			<c:when test="${empty requestScope.reqResult.result}">
				<div class="container  maincontent">
					<div class="row">
						<div
							class="col-lg-8 col-lg-offset-2 text-center bg-warning singlerow">
							<div class="singlerowele text-warning">
								<span class="glyphicon glyphicon-warning-sign"></span>
								&nbsp;&nbsp;<label>列表为空，请添加主题</label>
							</div>
						</div>
						<div class="col-lg-2 singlerow">
							<button type="button" class="btn btn-primary btn-lg"
								data-toggle="modal" data-target="#article_add_modal"
								onclick="readyToAddArticle()">
								<span class="glyphicon glyphicon-plus"></span> 添加
							</button>
						</div>
					</div>
				</div>
			</c:when>
			<c:otherwise>


				<div class="container-fluid  maincontent singlerow"
					style="margin-bottom: 20px">
					<button type="button" class="btn btn-primary btn-lg pull-right"
						data-toggle="modal" data-target="#article_add_modal"
						onclick="readyToAddArticle()">
						<span class="glyphicon glyphicon-plus"></span> 添加
					</button>
				</div>

				<div class="container-fluid maincontent">
					<table class="table table-hover">
						<thead class="bg-primary">
							<tr>
								<th class="text-center">类别</th>
								<th class="text-center">名称</th>
								<th class="text-center">描述</th>
								<th class="text-center">添加日期</th>
								<th class="text-center">最后回归日期</th>
								<th class="text-center">回归次数</th>
								<th class="text-center">目标回归日期</th>
								<th class="text-center">是否过期</th>
								<th class="text-center">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${requestScope.reqResult.result}" var="article"
								varStatus="articleStatus">

								<c:choose>
									<c:when test="${article.expire==1}">
										<tr class="bg-danger">
									</c:when>
									<c:otherwise>
										<tr class="bg-success">
									</c:otherwise>
								</c:choose>

								<td class="text-center">空</td>
								<td class="text-center"><c:out value="${article.name}" /></td>
								<td class="text-center">
									<div id="row<c:out value="${articleStatus.count}"/>" class="make-wrap" onclick="showArticleDetail('<c:out value="${articleStatus.count}"/>')">
										<c:out value="${article.detail}" />
									</div>
									<div id="row<c:out value="${articleStatus.count}"/>-hidden" class="hidden">
										<pre class="pre-scrollable text-left "
											style="max-width:460px；"><c:out value="${article.detail.trim()}" /></pre>
										<button type="button" class="btn btn-info pull-right"
											onclick="showArticleDetail('<c:out value="${articleStatus.count}"/>')">
											<span class="glyphicon glyphicon-chevron-up"></span> 收起
										</button>
									</div>
								</td>
								<input type="hidden" id='articleHidden<c:out value="${articleStatus.index}"/>' value='${article.detail}'/>
								<td class="text-center"><fmt:formatDate
										value="${article.alloDate}" pattern="yyyy-MM-dd" /></td>
								<td class="text-center"><fmt:formatDate
										value="${article.lastAlloDate}" pattern="yyyy-MM-dd" /></td>
								<td class="text-center"><c:out value="${article.alloTimes}" /></td>
								<td class="text-center"><fmt:formatDate
										value="${article.expectAlloDate}" pattern="yyyy-MM-dd" /></td>
								<td class="text-center"><c:choose>
										<c:when test="${article.expire==0}">否</c:when>
										<c:otherwise>是</c:otherwise>
									</c:choose></td>
								<td class="text-center">
									<button type="button" class="btn btn-success "
										data-toggle="modal" data-target="#article_record_modal" 
										onclick="readyToAddArticleRecord('<c:out value="${article.id}"/>')">
										<span class="glyphicon glyphicon-ok"></span> 回归
									</button>&nbsp;
									<button type="button" class="btn btn-info " data-toggle="modal"
										data-target="#article_add_modal" onclick="redayToModifyArticle('update','<c:out value="${article.name}"/>',
											'<c:out value="${articleStatus.index}"/>','<c:out value="${article.id}"/>')">
										<!-- onclick="var aaa='12321321\
												12321321\
												43432432'.replace(/\r\n/g,'\\').replace(/\n/g,'\\');alert(aaa);modifyArticle('update','11',aaa);" -->
										<span class="glyphicon glyphicon-pencil"></span> 修改
									</button>&nbsp;
									<button type="button" class="btn btn-danger "
										onclick="alert('尽请期待')">
										<span class="glyphicon glyphicon-check"></span> 结档
									</button>
								</td>
								</tr>
								
								
							</c:forEach>
						</tbody>
					</table>
				</div>

			</c:otherwise>
		</c:choose>


	</div>


	<div id="article_add_modal" class="modal fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
		data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">添加主题</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form" id="articleForm"
						method="post" action="<c:url value="/article/add"/>">
						<input type="hidden" id="actionTypeInHidden" name="actionType" value=""/>
						<input type="hidden" id="articleIdInHidden" name="id" value=""/>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label text-right">名称</label>
							<div class="col-sm-9 col-sm-offset-1">
								<input type="text" class="form-control" id="name" name="name"
									placeholder="请输入名称" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="detail" class="col-sm-2 control-label text-right">描述</label>
							<div class="col-sm-9 col-sm-offset-1">
								<textarea id="detail" name="detail" class="form-control"
									rows="5" placeholder="主题描述" required="required"
									style="resize: none"></textarea>
							</div>
						</div>

						<button id="article_add_modal_reset_btn" class="hidden"
							type="reset"></button>
					</form>
				</div>
				<div class="modal-footer">
					
					<button type="button" id="articleBtnAdd" class="btn btn-primary"
						onclick="addArticle()">确认新增</button>
					<button id="articleBtnDel" type="button" class="btn btn-danger hidden"
						onclick="delArticle()">删除</button>
					<button id="articleBtnUpdate" type="button" class="btn btn-info hidden"
						onclick="modifyArticle()">更新</button>
						
					<button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<script type="text/javascript">
		function readyToAddArticle() {
			$('#article_add_modal_reset_btn').click();
			$('#actionTypeInHidden').val('add');
			$('#articleIdInHidden').val('');
			$('#articleBtnAdd').removeClass('hidden');
			$('#articleBtnDel').addClass('hidden');
			$('#articleBtnUpdate').addClass('hidden');
		}
		function redayToModifyArticle(){
			$('#article_add_modal_reset_btn').click();
			$('#name').val(arguments[1]);
			$('#articleIdInHidden').val(arguments[3]);
			$('#detail').html($('#articleHidden'+arguments[2]).val());
			$('#actionTypeInHidden').val('update');
			$('#articleBtnAdd').addClass('hidden');
			$('#articleBtnDel').removeClass('hidden');
			$('#articleBtnUpdate').removeClass('hidden');
		}		
		function addArticle() {
			$('#articleForm').submit();
		}
		function modifyArticle(){
			$('#articleForm').attr('action','<c:url value="/article/modify"/>');
			$('#articleForm').submit();
		}
		
		function delArticle(){
			if(!confirm('是否要确定删除？')){
				return false;
			}
			$('#actionTypeInHidden').val('del');
			$('#articleForm').attr('action','<c:url value="/article/del"/>');
			$('#articleForm').submit();
		}
		
	</script>



	<div id="article_record_modal" class="modal fade" tabindex="-1"
		role="dialog" aria-labelledby="articleRecordLabel" aria-hidden="true"
		data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
			
				<!-- <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="articleRecordLabel">新增归档</h4>
				</div> -->
				
				<div class="modal-body">


					<ul id="myTab" class="nav nav-tabs" style="font-size:20px;">
						<li class="active"><a id="articleRecordAddA" href="#articleRecordTabAdd" onclick="backToAddArticleRecord()" data-toggle="tab" style="outline-style:none;">
								添加归档 </a></li>
						<li><a id="articleRecordListA" href="#articleRecordTabList" onclick="findAllArticleRecord()" data-toggle="tab" style="outline-style:none;">归档列表</a></li>
					</ul>

					<script type="text/javascript">
						function readyToAddArticleRecord(){
							currentArticleId=arguments[0];
							$('#article_record_modal_reset_btn').click();
							$('#recordArticleIdInHidden').val(currentArticleId);
							$('#articleRecordAddA').click();
						}
						function addArticleRecord() {
							$('#articleRecordForm').submit();
						}
						function backToAddArticleRecord(){
							if($('#articleRecordAddA').attr('href')=='#articleRecordTabAdd'){
								$('#articleRecordFooter button').removeAttr('disabled');
							}
						}
						function findAllArticleRecord(){
							$('#articleRecordAddA').attr('href','#');
							$('#articleRecordFooter button:not([name="closemodelbtn"])').attr('disabled','disabled');
							//alert('<c:url value="/articleRecord/findAll"/>');
							var dataobj={articleId:currentArticleId};
							$.ajax({
								url:'<c:url value="/articleRecord/findAll"/>',
								data:JSON.stringify(dataobj),
								dataType:'text',
								type:'post',
								contentType:'application/json; charset=utf-8',
								success:function(data,status,xhr){
									$('#accordion').html(data);
									$('#articleRecordAddA').attr('href','#articleRecordTabAdd');
								}
							});
						}
						function addArticleRecord(){
							if(arguments.length!=0){
								if(!confirm('是否确定跳过备注直接归档？')){
									return false;
								}
								$('#jumpRecordInHidden').val('true');	
							}else{
								$('#jumpRecordInHidden').val('false');	
							}
							$('#articleRecordForm').submit();
						}
					</script>
					<div id="myTabContent" class="tab-content" style="margin-top:30px">
					<div id="articleRecordTabAdd" class="tab-pane fade in active">
						<form class="form-horizontal" role="form" id="articleRecordForm"
							method="post" action="<c:url value="/articleRecord/add"/>">
							<input type="hidden" id="recordArticleIdInHidden" name="articleId"/>
							<input type="hidden" id="jumpRecordInHidden" name="jumpRecord"/>							
							<div class="form-group" style="margin-top:30px;">
								<label for="name" class="col-sm-2 control-label text-right">标题</label>
								<div class="col-sm-9 col-sm-offset-1">
									<input type="text" class="form-control"  name="title"
										placeholder="请输入标题" required="required">
								</div>
							</div>
							<div class="form-group" style="margin-top:30px;">
								<label class="col-sm-2 control-label text-right">状态</label>
								<div class="col-sm-9 col-sm-offset-1" >
										        <label for="aa1"><input type="radio" name="alloResult" id="aa1" value="1" checked style="outline-style:none;"/> 
										        	<span style="display:inline-block;position:relative;top:-5px">&nbsp;已完成</span></label>
										    &nbsp;&nbsp;&nbsp;
										        <label for="aa2"><input type="radio" name="alloResult" id="aa2" value="0" style="outline-style:none;"/>
										        	<span style="display:inline-block;position:relative;top:-5px">&nbsp;未完成</span></label>

								</div>
							</div>
							<div class="form-group" style="margin-top:30px;">
								<label for="detail" class="col-sm-2 control-label text-right">描述</label>
								<div class="col-sm-9 col-sm-offset-1">
									<textarea  name="detail" class="form-control"
										rows="5" placeholder="归档描述" required="required"
										style="resize: none"></textarea>
								</div>
							</div>


							<button id="article_record_modal_reset_btn" class="hidden"
								type="reset"></button>
						</form>
					</div>

					<div id="articleRecordTabList" class="tab-pane fade" >
						
						
					<div class="panel-group" id="accordion" style="max-height:700px">
					
					    
					</div>
						
						
						
					</div>
					</div>

				</div>
				<div class="modal-footer" id="articleRecordFooter">
					
					<button type="button" class="btn btn-info" data-dismiss="modal" onclick="addArticleRecord('jump')">跳过直接归档</button>
					<button type="button" class="btn btn-primary"
						onclick="addArticleRecord()">确认新增</button>
					<button type="button" class="btn btn-danger" name="closemodelbtn" data-dismiss="modal">关闭</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</div>
</body>
</html>

