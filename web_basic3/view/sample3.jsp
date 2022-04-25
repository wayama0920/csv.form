<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.*, dto.FileDto" %>

<%
List<FileDto> list = (List<FileDto>)request.getAttribute("list");
List<FileDto> deletelist = (List<FileDto>)request.getAttribute("deletelist");
String cmd = (String)request.getAttribute("cmd");

%>
<html>
<head>
	<title>削除画面</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
</head>
	<h2>削除画面</h2>
	<hr style="height:2; background-color:black;"></hr>


 	<%
 	if(cmd == null) {
 	%>
 	<h3>削除したい書籍(ISBN)を選択してください</h3>
	<table>
		<tr>
			<th>ISBN</th>
			<th>名前</th>
			<%
			for (FileDto s : list) {
			%>
		</tr>

		<tr>
			<td class="tdIsbn">
				<a href="<%=request.getContextPath()%>/fileServlet03?id=<%=s.getIsbn()%>&cmd=delete">
				<%=s.getIsbn()%></a>
			</td>
			<td class="tdtitle"><%=s.getTitle()%></td>
		</tr>
			<%
			}
			%>
	</table>
 	<%
 	}else{
 	%>

 		<h3>下記を削除しますか？</h3>

	 	<%
	 	for (FileDto s : deletelist) {
		%>
		<table>
	 		<tr>
				<th>ISBN</th>
				<td class="tdtitle"><%=s.getIsbn()%></td>
			</tr>

			<tr>
				<th>名前</th>
				<td class="tdtitle"><%=s.getTitle()%></td>
			<tr>

			<tr>
				<th>価格</th>
				<td class="tdtitle"><%=s.getPrice()%></td>
			</tr>
			<tr>
				<th>著者</th>
				<td class="tdtitle"><%=s.getAuthor()%></td>
			</tr>

			<tr>
				<th>コメント</th>
				<td class="tdtitle"><%=s.getComment()%></td>
			</tr>
	 	</table>


	 <table class="aa">
		<tr>
			<td class="aa">
				<form action="<%=request.getContextPath()%>/fileServlet03">
					<input type="submit" value="はい" name="ok">
					<input type = "hidden" name="id" value="<%=s.getIsbn()%>">
				</form>
			</td>
			<td class="ba">
				<form action="<%=request.getContextPath()%>/fileServlet">
					<input type="submit" value="いいえ" >
				</form>
			</td>
		</tr>
	</table>


	 	<%
	 	}
	 	%>

 	<%
 	}
 	%>
</body>
</html>