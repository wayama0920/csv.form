<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import = "java.util.*,dto.FileDto" %>
<%
List<FileDto> list = (List<FileDto>) request.getAttribute("list");
%>

<html>
<head>
	<title>詳細画面</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
</head>
<body>

<h2>詳細画面</h2>
	<hr style="height:2; background-color:black;"></hr>

	<table>

	<%
	if(list != null) {

		for(FileDto s : list) {
	%>
		<tr>
			<th>ISBN</th>
			<td><%=s.getIsbn()%></td>
		</tr>

		<tr>
			<th>名前</th>
			<td><%=s.getTitle()%></td>
		<tr>

		<tr>
			<th>価格</th>
			<td><%=s.getPrice()%></td>
		</tr>

		<tr>
			<th>著者</th>
			<td><%=s.getAuthor()%></td>
		</tr>

		<tr>
			<th>コメント</th>
			<td><%=s.getComment()%></td>
		</tr>
	</table>

	<form action = "<%=request.getContextPath() %>/view/sample5.jsp">
	<input class="submit" type="submit" value="変更">
	<input type = "hidden" name="isbn" value="<%=s.getIsbn()%>">
	</form>
		<%
			}
		}
		%>

	<a class="a"  href="<%=request.getContextPath() %>/fileServlet">一覧画面に戻る</a>
</body>
</html>