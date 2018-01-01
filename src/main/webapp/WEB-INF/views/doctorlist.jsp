<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>就诊时间段选择</title>
	<!-- 引入JQuery -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/JQuery/jquery.js"></script>
	<!-- 引入 Bootstrap -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/Bootstrap/css/bootstrap.min.css">
	<script src="${pageContext.request.contextPath}/Bootstrap/js/bootstrap.min.js"></script>
	<!-- 自定义的CSS -->
	<!-- 自定义的JavaScript -->
	<script>

        var doctorList;
		$(document).ready(function(){
		    if(sessionStorage.searchCode == 0) {
                $(".navbar-brand").append(sessionStorage.catname);

                $.ajax({
                    //url: "http://localhost:8080/customer/doctors?categoryid=" + sessionStorage.catid,
                    url:'http://wulei.kim:8080/hosporder_snapshot_v1/customer/doctors?categoryid=' + sessionStorage.catid,
                    type: "GET",
                    success: function (data) {
                        dataObj = JSON.parse(data);
                        doctorList = dataObj;
                        for (var i in dataObj) {
                            $(".dropdown-menu").append(
                                '<li><a id="' + dataObj[i]['doctorid'] + '" name="' + dataObj[i]['doctorid'] + '" onclick="doctorSelected(this)">' + dataObj[i]['name'] + '</a></li>'
                            )
                        }
                    }
                })
            }
            else if(sessionStorage.searchCode == 1) {
		        displaySearchDoctor();
            }
		});

		function displaySearchDoctor(){

            $(".navbar-brand").append(sessionStorage.SDname);

		    var id = sessionStorage.SDid;
		    var doctorName = sessionStorage.SDname;
		    var doctorDescription = sessionStorage.SDdescription;
		    console.log(sessionStorage.searchCode);

            $.ajax({
                //url:'http://localhost:8080/customer/dates?doctorid=' + id,
                url:'http://wulei.kim:8080/hosporder_snapshot_v1/dates?doctorid=' + id,
                type:'GET',
                success:function(data){
                    dataObj = JSON.parse(data);
                    $(".review").empty();
                    for(var i in dataObj){
                        dateStr = dataObj[i]['name'];
                        dateStrArr = dateStr.split("@");
                        dayArr = dateStrArr[1].split("-");
                        date = dayArr[1]+'月'+dayArr[2]+"日 "+dateStrArr[0] +' ' + dateStrArr[2] + '至' + dateStrArr[3];

                        $(".review").append(
                            '<div class="review-bd" id="'+dataObj[i]['dateid']+'" onclick="dateSelected(this)">\n' +
                            '        \t<!-- 文字标题 第一行 -->\n' +
                            '            <h3><a>'+ date +'</a></h3>\n' +
                            '            <!--  第二行-->\n' +
                            '            <div class="review-meta">\n' +
                            '                <a>医生:</a> ' +doctorName+
                            '                \n' +
                            '\n' +
                            '<a>\t剩余预约:</a>' + dataObj[i]['number'] + '人' +
                            '            </div>\n' +
                            '            <!-- 第三行 描述 -->\n' +
                            '            <div class="review-content">\n' + '医生简介：' +
                            doctorDescription +
                            '            </div>\n' +
                            '        </div>'
                        )
                    }
                }
            });


        }

        function dateSelected(date){
            sessionStorage.dateId = date.id;
            //window.location.href = "http://localhost:8080/pages/customer/customer";
            window.location.href = "http://wulei.kim:8080/hosporder_snapshot_v1/pages/customer/customer";

        }

		function doctorSelected(doctor) {
		    var id = doctor.id;
		    var doctorSelected = document.getElementsByName(id);
		    var doctorName = doctorSelected[0].innerHTML;
		    var doctorDescription;
		    for(var i in doctorList){
		        if(doctorList[i]['doctorid'] == id)
		            doctorDescription = doctorList[i]['description'];
            }
            if(doctorDescription == undefined){
		        doctorDescription = '无';
            }



            $.ajax({
                //url:'http://localhost:8080/customer/dates?doctorid=' + doctor.id,
                url:'http://wulei.kim:8080/hosporder_snapshot_v1/customer/dates?doctorid=' + doctor.id,
                type:'GET',
                success:function(data){
                    dataObj = JSON.parse(data);
                    $(".review").empty();
                    for(var i in dataObj){
                        dateStr = dataObj[i]['name'];
                        dateStrArr = dateStr.split("@");
                        dayArr = dateStrArr[1].split("-");
                        date = dayArr[1]+'月'+dayArr[2]+"日 "+dateStrArr[0] +' ' + dateStrArr[2] + '至' + dateStrArr[3];

                        $(".review").append(
                            '<div class="review-bd" id="'+dataObj[i]['dateid']+'" onclick="dateSelected(this)">\n' +
                            '        \t<!-- 文字标题 第一行 -->\n' +
                            '            <h3><a>'+ date +'</a></h3>\n' +
                            '            <!--  第二行-->\n' +
                            '            <div class="review-meta">\n' +
                            '                <a>医生:</a> ' +doctorName+
                            '                \n' +
                            '\n' +
                            '<a>\t剩余预约:</a>' + dataObj[i]['number'] + '人' +
                            '            </div>\n' +
                            '            <!-- 第三行 描述 -->\n' +
                            '            <div class="review-content">\n' + '医生简介：' +
                            doctorDescription +
                            '            </div>\n' +
                            '        </div>'
                        )
                    }
                }
            });

        }
	</script>
</head>

<body>
	<!-- Part  1 -->
	<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- 1 科室名称 -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <!-- part 1 -->
      <a class="navbar-brand" href="#"></a>
    </div>

    
    <!-- Part 2 -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a>请选择 <span class="sr-only">(current)</span></a></li>

        <!-- <li><a href="#">Link</a></li> -->
		<!--2.1 医生列表 -->
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">医生 <span class="caret"></span></a>
          <ul class="dropdown-menu">

            <!-- <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">One more separated link</a></li> -->
          </ul>
        </li>
      </ul>

      <!-- 2.2 确定预约时间 -->
      <form class="navbar-form navbar-left">
			<!-- 2.2.1 选择预约  year-month-day -->
        <div class="form-group">
        	<div style="width: 80%" class="input-group date form_date col-md-5" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
				                <input  class="form-control" size="16" type="text" value="111" readonly>
				                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
								<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
			</div>
        </div>
			<!-- 2.2.2 选择预约  time XX:XX -->
        <div class="form-group">
        	<div  style="width: 80%" class="input-group date form_time col-md-5" data-date="" data-date-format="hh:ii" data-link-field="dtp_input3" data-link-format="hh:ii">
					                <input class="form-control" size="16" type="text" value="" readonly>
                    				<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
		 </div>
        </div>
			<!-- 2.2.3 确定预约 -->
        <button type="submit" class="btn btn-default">查看</button>

      </form>
    

    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<!-- Part2 显示符合条件的医生的清单List  -->

	 <div class="review ">
	 	<!--  Left 得到一张图片
        <div class="review-hd">
            <a href="https://book.douban.com/review/8980600/">
              <img class="lazy"
              data-original="https://img3.doubanio.com/mpic/s29587881.jpg"
              src="${pageContext.request.contextPath}/image/b.jpg"
              alt="记：一场两百年前的大雾霾" />
            </a>
        </div>
         right 文字 -->
         <!--
        <div class="review-bd">
        	<!-- 文字标题 第一行 -
            <h3><a href="customer.jsp">预约</a></h3>
            <!--  第二行--
            <div class="review-meta">
                <a href="#">姓名：</a> 张医生
                

				<a href="#">姓名：</a> 049523
                <span class="allstar40"></span>
            </div>
            <!-- 第三行 描述 --
            <div class="review-content">
              	擅长XXX，有XXX年经验...
                <!-- <a href="https://book.douban.com/review/8980600/">(全文)</a> -
            </div>
        </div>
    -->
    </div>









<!-- 引入日历 CSS -->
	<link href="${pageContext.request.contextPath}/Bootstrap/dataTimeCss/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
	<script type="text/javascript" src="${pageContext.request.contextPath}/Bootstrap/dataTimeJs/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Bootstrap/dataTimeJs/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>
<!-- 继续引入 CSS -->
	<link href="${pageContext.request.contextPath}/CSS/another.css" rel="stylesheet" media="screen">
	<link href="${pageContext.request.contextPath}/CSS/master.css" rel="stylesheet" media="screen">

	<!-- script -->
	<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        //language:  'fr',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1
    });
	$('.form_date').datetimepicker({
        language:  'fr',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
    });
	$('.form_time').datetimepicker({
        language:  'fr',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 1,
		minView: 0,
		maxView: 1,
		forceParse: 0
    });
</script>
</body>

</html>