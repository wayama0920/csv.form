<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import = "java.util.*,dto.FileDto,dao.FileOut,dao.FileIn" %>
<%
List<FileDto> searchList = (List<FileDto>) request.getAttribute("searchList");
String message = (String) request.getAttribute("message");
String name = (String) request.getAttribute("name");

%>

<html>
<head>
	<title>検索画面</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
</head>
<body>

<h2>検索画面</h2>
	<hr style="height:2; background-color:black;"></hr>

	<%
	if( searchList == null &&  message ==null) {
	%>
	<form action="<%=request.getContextPath()%>/fileServlet04">
	<h3>お探しの名前を入力してください。</h3>
	<table class="Atable">
		<tr class = "input">
			<th class="input">名前</th>
			<td class="input"><input class="input"type="text" name="name"></td>
		<tr>
	</table>
 	<input class="submit" type="submit" value="検索">
	</form>

	<%
	}else if( message != null) {
	%>

	<form action="<%=request.getContextPath()%>/fileServlet04">
	<h3>お探しの名前又ISBN番号を入力してください。</h3>
	<table class="Atable">
		<tr>
			<th>名前</th>
			<td><input type="text" name="name"></td>
		<tr>
	</table>

 		<p class="p"><%=message%></p>

	<input class="submit" type="submit" value="検索">
	</form>

	<%
	}else {
	%>
		<h3>検索結果</h3>
		<h4>検索情報："<%=name %>"</h4>
		<%
	 	for (FileDto s : searchList) {
		%>

		<table>
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
	 <%
	 	}
	 %>

	 	<a class="a" href="<%=request.getContextPath() %>/fileServlet">一覧画面に戻る</a>
	<%

	}
	%>


</body>
</html>