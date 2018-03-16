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
<link rel="stylesheet" type="text/css" href="<c:url value='/js/simple-calendar/stylesheets/simple-calendar.css'/>">
<script type="text/javascript" src="<c:url value='/js/simple-calendar/javascripts/simple-calendar.js'/>"></script>
<style type="text/css">
	body a{
		outline-style:none;
	}
	body a:hover{
		text-decoration:none;
	}
	body a:click{
		text-decoration:none;
	}
	body a:active{
		text-decoration:none;
	}
　　a:visited{
		text-decoration:none;
	}
	a:focus{
		text-decoration:none;
	}
	.panelstyle{
		border:none;
	}
	.titlestyle{
		color:grey;
		font-size:20px;
	}
	.delbtn{
		position:absolute;
		right:1%;
		top:5px;
	}
	body pre{
		border:none;
		background-color:transparent;
	}
	.blockseperator{
		margin-top:40px;
	}
	.calblock{
		max-width:95%;
		display:inline-block;
		position:relative;
		margin-top:40px;
		left:40px;
	}
	.recordheader{
		border-radius:5px;
		height:40px;
		line-height:40px;
	}
</style>
<script type="text/javascript">
	var options = {
	      width: '400px',
	      height: '400px',
	      language: 'CH', //语言
	      showLunarCalendar: true, //阴历
	      showHoliday: true, //休假
	      showFestival: true, //节日
	      showLunarFestival: true, //农历节日
	      showSolarTerm: false, //节气
	      showMark: true, //标记
	      timeRange: {
	        startYear: 1900,
	        endYear: 2049
	      },
	      mark: {
	        '2016-5-5': '上学'
	      },
	      theme: {
	        changeAble: false,
	        weeks: {
	          backgroundColor: '#FBEC9C',
	          fontColor: '#4A4A4A',
	          fontSize: '20px',
	        },
	        days: {
	          backgroundColor: '#ffffff',
	          fontColor: '#565555',
	          fontSize: '24px'
	        },
	        todaycolor: 'orange',
	        activeSelectColor: 'orange',
	      }
	    };
	function selectDayCallback(){
		alert(arguments[0]);
	}
	$(function(){
		var myCalendar = new SimpleCalendar('#calblock',options,selectDayCallback,false);
		refreshWorkRecord();
	});
	
	function refreshWorkRecord(){
		var calblockele=document.querySelector('#calblock');
		$.ajax({
			url:'<c:url value="/workrecord/findCurmonthAll"/>',
			dataType:'text',
			success:function(data){
				$('#workrecordblock1').html(data);
			}
		});
	}
</script>
</head>
<body>
	<div class="container-fluid">
		<div class="col-lg-3 col-lg-offset-1 blockseperator" style="border-right: solid 2px rgb(246,214,255)">
			<div id="calblock" class="calblock"></div>
		</div>
		<div class="col-lg-7 blockseperator">
			<div id="workrecordblock1">
			
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		function readyToAddWorkRecord(){
			$('#workrecord_modal_reset_btn').click();
			$('#workRecordIdInHidden').val('');
		}
		function addWorkRecord(){
			var dataobj={
				title:$('#workrecord_title').val(),
				content:$('#workrecord_content').val()
			};
			var datastr=JSON.stringify(dataobj);
			$.ajax({
				url:'<c:url value="/workrecord/addWorkRecord"/>',
				data:datastr,
				type:'post',
				dataType:'json',
				contentType:'application/json;charset=utf-8',
				success:function(data){
					$('#workRecordBtnClose').click();
					refreshWorkRecord();
				}
			});
		}
		
		
		function delWorkRecord(){
			if(!confirm('是否要确定删除该日志？')){
				return false;
			}
			$('#workrecord_modal_reset_btn').click();
			$('#workRecordIdInHidden').val(arguments[0]);
			var recordId=arguments[0];
			$.ajax({
				url:'<c:url value="/workrecord/del/"/>'+recordId,
				dataType:'json',
				success:function(data){
					refreshWorkRecord();
				}
			});
		}
	</script>
	
	<div id="work_record_add_modal" class="modal fade" tabindex="-1"
		role="dialog" aria-labelledby="workRecordModalLabel" aria-hidden="true"
		data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="workRecordModalLabel">添加日志</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form" id="workRecordForm"
						method="post" action="<c:url value="/workrecord/add"/>">
						<input type="hidden" id="workRecordIdInHidden" name="id"/>
						<div class="form-group">
							<label for="workrecord_title" class="col-sm-2 control-label text-right">标题</label>
							<div class="col-sm-9 col-sm-offset-1">
								<input type="text" class="form-control" id="workrecord_title" name="title"
									placeholder="请输入标题">
							</div>
						</div>
						<div class="form-group">
							<label for="workrecord_content" class="col-sm-2 control-label text-right">内容</label>
							<div class="col-sm-9 col-sm-offset-1">
								<textarea id="workrecord_content" name="content" class="form-control"
									rows="5" placeholder="内容描述" required="required"
									style="resize: none"></textarea>
							</div>
						</div>

						<button id="workrecord_modal_reset_btn" class="hidden"
							type="reset"></button>
					</form>
				</div>
				<div class="modal-footer">
					
					<button type="button" id="workRecordBtnAdd" class="btn btn-primary"
						onclick="addWorkRecord()">确认新增</button>
					<button type="button" id="workRecordBtnClose" class="btn btn-danger" data-dismiss="modal">关闭</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</body>
</html>