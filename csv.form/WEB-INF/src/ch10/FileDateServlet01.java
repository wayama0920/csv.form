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

public class FileDateServlet01 extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//ファイルの取得、書き込みなど、サーブレット通信するために使用するメソッド
		ServletContext application = this.getServletContext();

		FileDto fileDto = new FileDto();

		List<FileDto> list = (List<FileDto>) application.getAttribute("list");


		try {
			//ServletContextでgetRealPathを使いファイルを読み込む
			String path = application.getRealPath("/file/sample.csv");

			//idをsample.jspから取得
			String id = request.getParameter("id");

			//FileDaoをオブジェクト化
			FileDao fileDao = new FileDao();

			//selectinfoメゾットにファイルとidを入れ呼び出す
			list = fileDao.selectInfo(id,path);

		}catch(Exception e) {

		}finally {
			request.setAttribute("list", list);
			request.getRequestDispatcher("/view/sample1.jsp").forward(request, response);
		}
	}
}
