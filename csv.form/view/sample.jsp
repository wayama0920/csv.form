<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.*, dto.FileDto" %>

<%
List<FileDto> list = (List<FileDto>)request.getAttribute("list");

%>
<html>
<head>
	<title>l一覧画面</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
</head>
	<h2>一覧画面</h2>
	<hr style="height:2; background-color:black;"></hr>

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
				<a href="<%=request.getContextPath()%>/fileServlet01?id=<%=s.getIsbn()%>">
				<%=s.getIsbn()%></a>
			</td>
			<td class="tdtitle"><%=s.getTitle()%></td>
		</tr>
			<%
			}
			%>
	</table>






	<table class="aa">
		<tr >
			<td class="aa">
				<form action="<%=request.getContextPath()%>/view/sample2.jsp">
					<input type="submit" value="登録">
				</form>
			</td>
			<td class="ba">
				<form action="<%=request.getContextPath()%>/fileServlet03">
					<input type="submit" value="削除">
				</form>
			</td>
			<td class="ca">
				<form action="<%=request.getContextPath()%>/view/sample4.jsp">
					<input type="submit" value="検索">
				</form>
			</td>
		</tr>
	</table>

</body>
</html>