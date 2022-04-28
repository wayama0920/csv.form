<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import = "java.util.*,dto.FileDto,dao.FileOut,dao.FileIn" %>
<%
List<FileDto> list = (List<FileDto>) request.getAttribute("list");
String message = (String) request.getAttribute("message");
%>

<html>
<head>
	<title>新規登録画面</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
</head>
<body>

<h2>新規登録画面</h2>
	<hr style="height:2; background-color:black;"></hr>

	<form action="<%=request.getContextPath()%>/fileServlet02">
	<table class="Atable">
		<tr>
			<th class="thIsbn">ISBN</th>
 			<td><input class="input" type="text" name="isbn"></td>
 		</tr>
		<tr>
			<th class="thIsbn">名前</th>
			<td><input class="input" type="text" name="title"></td>
		<tr>

		<tr>
			<th class="thIsbn">価格</th>
			<td><input class="input" type="text" name="price"></td>
		</tr>

		<tr>
			<th class="thIsbn">著者</th>
			<td><input class="input" type="text" name="author"></td>
		</tr>

		<tr>
			<th class="thIsbn">コメント</th>
			<td><input class="input" type="text" name="comment"></td>
		</tr>
	</table>

	<table class="tdmessage"><%if( message != null) {%>
 				<p><%=message%></p>
 			<% }%>
 	</table>

	<input class="submit" type="submit" value="登録">


	</form>


</body>
</html>