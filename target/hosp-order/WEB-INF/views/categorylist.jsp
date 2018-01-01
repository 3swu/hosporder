<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-cmn-Hans" class="ua-windows ua-webkit">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="renderer" content="webkit">
    <meta name="referrer" content="always">
   		 <title>
       		第一个页面
		</title>
    
    <meta name="baidu-site-verification" content="cZdR4xxR7RxmM4zE" />
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Expires" content="Sun, 6 Mar 2005 01:00:00 GMT">
    
    <meta http-equiv="mobile-agent" content="format=xhtml; url=http://m.douban.com/movie/">
    <meta property="qc:admins" content="13753521351564752166375" />
    <!-- 引入JQuery -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/JQuery/jquery.js"></script>
	<!-- 引入 Bootstrap -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/Bootstrap/css/bootstrap.min.css">
	<script src="${pageContext.request.contextPath}/Bootstrap/js/bootstrap.min.js"></script>
    <!--  -->
	 <link href="${pageContext.request.contextPath}/CSS/douban.css" rel="stylesheet" type="text/css">
	 <link href="${pageContext.request.contextPath}/CSS/douban1.css" rel="stylesheet" type="text/css">
	 <link href="${pageContext.request.contextPath}/CSS/bundle.css" rel="stylesheet" type="text/css">

	 <!-- 自定义Style -->
	<style type="text/css" media="screen">
		#billboard {position: absolute;left:50%;top:20%; 
		margin-left:-200px;margin-top:-100px;} 
	</style>
    <script>
        var dataObj;

        function clickfoo(cat) {
            sessionStorage.catid = cat.id;
            sessionStorage.searchCode = 0;
            for(var i in dataObj){
                if(dataObj[i]['catid'] == cat.id){
                    sessionStorage.catname = dataObj[i]['catname'];
                }
            }
            //window.location.href = "http://localhost:8080/pages/customer/doctor";
            window.location.href = "http://wulei.kim:8080/hosporder_snapshot_v1/pages/customer/doctor";
        }

        $(document).ready(function(){
            $.ajax({
                //url:'http://localhost:8080/customer/categories',
                url:'http://wulei.kim:8080/hosporder_snapshot_v1/customer/categories',
                type:'GET',
                success:function(data){
                    dataObj = JSON.parse(data);
                    for(var i in dataObj){
                        $(".list").append(
                            '<tr>\n' +
                            '\n' +
                            '<td><a id="'+dataObj[i]['catid']+'" onclick="clickfoo(this)" >'+dataObj[i]['catname']+'</a></td>\n' +
                            '</tr>'
                        )
                    }
                }
            });

            $("#search-btn").click(function () {
                var searchStr = $(".form-control").val();
                if(searchStr != ""){
                    $.ajax({
                        //url:'http://localhost:8080/customer/category-doctor?name=' + searchStr,
                        url:'http://wulei.kim:8080/hosporder_snapshot_v1/customer/category-doctor?name=' + searchStr,
                        type:'GET',
                        success:function (data) {
                            if(data == ""){
                                alert("未搜索到指定科室或医生！");
                            }
                            else {
                                var dataObj = JSON.parse(data);
                                if (dataObj['catname'] !== undefined) {
                                    $(".list").empty();
                                    $(".list").append(
                                        '<tr>\n' +
                                        '\n' +
                                        '<td><a id="' + dataObj['catid'] + '" onclick="clickfoo(this)" >' + dataObj['catname'] + '</a></td>\n' +
                                        '</tr>'
                                    );
                                }
                                else if (dataObj['description'] !== undefined) {
                                    //console.log(dataObj['doctorid']);

                                    sessionStorage.searchCode = 1;
                                    sessionStorage.SDid = dataObj['doctorid'];
                                    sessionStorage.SDname = dataObj['name'];
                                    sessionStorage.SDdescription = dataObj['description'];
                                    //window.location.href = "http://localhost:8080/pages/customer/doctor";
                                    window.location.href = "http://wulei.kim:8080/hosporder_snapshot_v1/pages/customer/doctor";
                                }
                            }
                        }
                    })
                }
            })
        })
    </script>
 </head>
<body>
	
	<!-- Part3 显示信息界面 -->
	<div id="billboard" class="s" data-dstat-areaid="75" data-dstat-mode="click,expose">
        <div class="billboard-hd">
           
           <div class="input-group col-md-3" style="margin-top:0px;positon:relative">
       		<input type="text" class="form-control"placeholder="请输入科室或医生" />
            <span class="input-group-btn">  
               <button class="btn btn-info btn-search" id="search-btn">查找</button>
            </span>  
 		</div>
        </div>
        <div class="billboard-bd">
            <table class="list">

                    
            </table>
        </div>
    </div>

</body>
</html>


