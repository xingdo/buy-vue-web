<%--
  Created by IntelliJ IDEA.
  User: admin1902
  Date: 2019/4/2
  Time: 9:48
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%@include file="/WEB-INF/head.jsp"%>
<body>
<form action="/upload/newSubmit" enctype="multipart/form-data" method="post">
            <input type="text" name="id"><br/>
            <input type="text" name="subdescribe"><br/>
            上传文件1：<input type="file" name="files"><br/>
            上传文件2：<input type="file" name="files"><br/>
    上传文件2：<input type="file" name="files"><br/>
    上传文件2：<input type="file" name="files"><br/>
             <input type="submit" value="提交">
</form>


<form action="/upload/issue" enctype="multipart/form-data" method="post">
    eventnoticeid<input type="text" name="eventnoticeid"><br/>
    mainid<input type="text" name="mainid"><br/>
    mydescribe<input type="text" name="mydescribe"><br/>
    address<input type="text" name="address"><br/>
    donehour<input type="text" name="donehour"><br/>
    上传文件1：<input type="file" name="files"><br/>
    上传文件2：<input type="file" name="files"><br/>
    上传文件2：<input type="file" name="files"><br/>
    上传文件2：<input type="file" name="files"><br/>
    <input type="submit" value="提交">
</form>


<form action="/employee/changeHead" enctype="multipart/form-data" method="post">
    <input type="text" name="id"><br/>
    上传头像：<input type="file" name="img"><br/>
    <input type="submit" value="提交">
</form>
</body>
</html>
