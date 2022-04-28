package ch10;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FileDao;
import dto.FileDto;

public class FileDateServlet04 extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		ServletContext application = this.getServletContext();

		//ServletContextにlistを追加している
		List<FileDto> list = (List<FileDto>) application.getAttribute("list");

		//FileDtoをオブジェクト化
		FileDto fileDto = new FileDto();

		//FileDaoをオブジェクト化
		FileDao fileDao =new FileDao();


		//name,idをjspファイルから取得
		String name = "";
		name = request.getParameter("name");

		String message = null;

		try {

			if(list == null) {
				//ServletContextでgetRealPathを使いファイルを読み込む
				String path = application.getRealPath("/file/sample.csv");
				//getListクラスにファイルを入れ呼び出す
				list = fileDao.getList(path);
				request.setAttribute("list", list);
			}

			 if(name.equals("")) {
				 message = "※名前を入力してください。";
			 }else {
				 while(true) {
					 for(int i = 0; i < list.size(); i++) {
						 message = null;
						 fileDto = list.get(i);
						 Pattern pattern = Pattern.compile(name);
						 if(pattern.matcher(fileDto.getTitle()).find()) {
							 break;
						 }
						 message = "※入力した名前が存在しません。再度ご入力ください";
						 continue;
					 }
					 break;
				 }
			 }



		}catch(Exception e) {

		}finally {
			if(message != null) {
				request.setAttribute("message", message);
				request.getRequestDispatcher("/view/sample4.jsp").forward(request, response);
			}else if(name != null){

				//ServletContextでgetRealPathを使いファイルを読み込む
				String path = application.getRealPath("/file/sample.csv");


				//selectinfoメゾットにpathとidを入れ呼び出す
				list = fileDao.searchList(name,path);

				request.setAttribute("name",name);
				request.setAttribute("searchList", list);
				request.getRequestDispatcher("/view/sample4.jsp").forward(request, response);
			}
		}

	}

}
