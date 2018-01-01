<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>科室信息列表</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, user-scalabel=no">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<!--<style >
		*{/*通配符*/
			margin: 0;/*外边距*/
			padding: 0;/*内边距*/
			border: 0;
		}

		#box{ /*id 选择器*/
			width: 514px;
			height: 600px;
			background:#ccffff;
			margin: 50px auto;/*外边距:上下50px 左右自动* 效果： 自适应居中*/
		}
		.head{
			width: 514px;
			height: 88px;
			text-align: center;
			background: #000;
			border: 1px;
		}

		.title{

			width: 200px;
			height: 60px;
			color: white;
			margin: 10px auto;
			padding: 30px;
		}
		/*第二部分*/
		.search{
			width: 514px;
			height: 50px;

		}
		.searchText{
			width: 480px;
			height: 30px;
			margin: 10px 20px;
		}
		/*第三部分*/
		.departmentList{
			width: 514px;
			height: 470px;
			padding-top: 8px;
		}
		li{
			height: 24px;
			padding-top: 3px;
			border-bottom:1px solid #000;

		}

		h5{
			width: 514px;
			height: 20px;
			padding-left: 12px;
		}
		img{
			float: right;
			width: 10px;
			height: 20px;
			padding-right: 24px;
		}
	</style> -->
		<script >
			function onDepartment(cat)
			{
			    sessionStorage.catid = cat.id;
			    window.location.href = "http://localhost:8080/pages/customer/doctor";
                //window.location.href = "http://wulei.kim:8080/hosporder_snapshot_v1/pages/customer/doctor";
			}
		  /*用于定义所有的document的时间响应函数*/
			$(document).ready(function(){
			    $.ajax({
                    url:'http://localhost:8080/customer/categories',
                    //url:'http://wulei.kim:8080/hosporder_snapshot_v1/customer/categories',
                    type:'GET',
                    success:function(data){
                        dataObj = JSON.parse(data);
                        for(var i in dataObj){
                            $(".departmentList").append(
                                '<li id="' + dataObj[i]['catid'] + '" onclick=onDepartment(this)>\n' +
                                "<h5 >\n" +
                                dataObj[i]['catname'] +
                                "<span><img  src=\"${pageContext.request.contextPath}/images/next.png\"></span>\n" +
                                "</h5>\n" +
                                "</li>"
                            )
                        }
                    }
                })

			});
	</script>
</head>
<body >
	<div id="box">
		<div class="head" ><!--大标题 -->
			<h2  class="title" >科室选择</h2>
		</div>

		<div class="search"><!--搜索栏 -->
			<form>
				<input class="searchText" type="search" placeholder="搜索医科或医生">
			</form>

		</div>

		<hr  size=4 color="green">

		<div ><!--科室信息列表 -->
			<ul class="departmentList" style="list-style-type:none" >

			</ul>
		</div>
	</div>

</body>
</html>