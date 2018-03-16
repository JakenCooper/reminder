<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
		var myCalendar = new SimpleCalendar('#calblock',options,selectDayCallback);
	});
</script>
</head>
<body>
<div id="calblock" class="calblock"></div>
</body>
</html>