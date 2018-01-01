<%@ page pageEncoding="UTF-8"%>
<html>
<head>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
    <script>
        $(document).ready(function () {
            $("#btn").click(function () {
                $.ajax({
                    url:'http://localhost:8080/customer/customer',
                    type:'POST',
                    contentType: 'application/json;charset=utf-8',
                    data:JSON.stringify({"name":"耿艺星","mobile":13888888888}),
                    success:function (data) {
                        console.log(data);
                    }
                })
            })
        })
    </script>
</head>
<body>


<input type="button" value="预约" id="btn">
</body>
</html>
