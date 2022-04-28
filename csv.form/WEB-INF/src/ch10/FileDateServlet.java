package ch10;



import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FileDao;
import dto.FileDto;

public class FileDateServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

		//ファイルの取得、書き込みなど、サーブレット通信するために使用するメソッド
		ServletContext application = this.getServletContext();

//		//ServletContextにlistを追加している
		List<FileDto> list = (List<FileDto>) application.getAttribute("list");

		String message = (String) request.getAttribute("message");

		try {
			if(list == null) {
				//ServletContextでgetRealPathを使いファイルを読み込む
				String path = application.getRealPath("/file/sample.csv");
				FileDao fileDao = new FileDao();

				//getListクラスにファイルを入れ呼び出す
				list = fileDao.getList(path);
				request.setAttribute("list", list);
			}

		}catch(Exception e) {

		}finally {
				request.getRequestDispatcher("/view/sample.jsp").forward(request, response);
		}

	}

}
