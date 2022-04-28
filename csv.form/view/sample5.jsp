<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import = "java.util.*,dto.FileDto,dao.FileOut,dao.FileIn" %>
<%
List<FileDto> list = (List<FileDto>) request.getAttribute("list");
String message = (String) request.getAttribute("message");
String isbn = request.getParameter("isbn");
%>

<html>
<head>
	<title>変更画面</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
</head>
<body>

<h2>変更画面</h2>
	<hr style="height:2; background-color:black;"></hr>

	<form action="<%=request.getContextPath()%>/fileServlet05">
	<table class="Atable">
		<tr>
			<th>ISBN</th>
 			<td><input type="text" name="isbn" value="<%=isbn %>"></td>
 		</tr>
		<tr>
			<th>名前</th>
			<td><input type="text" name="title" ></td>
		<tr>

		<tr>
			<th>価格</th>
			<td><input type="text" name="price"></td>
		</tr>

		<tr>
			<th>著者</th>
			<td><input type="text" name="author"></td>
		</tr>

		<tr>
			<th>コメント</th>
			<td><input type="text" name="comment"></td>
		</tr>
	</table>
	<input class="submit" type="submit" value="変更">
	</form>

	<a class="a"  href="<%=request.getContextPath() %>/fileServlet">一覧画面に戻る</a>


</body>
</html>