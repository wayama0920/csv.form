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

public class FileDateServlet03 extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		ServletContext application = this.getServletContext();

		//ServletContextにlistを追加している
		List<FileDto> list = (List<FileDto>) application.getAttribute("list");

		//FileDtoをオブジェクト化
		FileDto fileDto = new FileDto();

		//FileDaoをオブジェクト化
		FileDao fileDao =new FileDao();

		//cmdをjspファイルから取得
		String cmd = "";
		cmd =request.getParameter("cmd");

		//okをjspファイルから取得
		String ok = "";
		ok = request.getParameter("ok");


		try {

			if(list == null) {
				//ServletContextでgetRealPathを使いファイルを読み込む
				String path = application.getRealPath("/file/sample.csv");
				//getListクラスにファイルを入れ呼び出す
				list = fileDao.getList(path);
				request.setAttribute("list", list);
			}

		}catch(Exception e) {

		}finally {
			if(cmd == null && ok == null) {
				request.getRequestDispatcher("/view/sample3.jsp").forward(request, response);
			}else if(cmd != null){

				//ServletContextでgetRealPathを使いファイルを読み込む
				String path = application.getRealPath("/file/sample.csv");

				//idをsample.jspから取得
				String id = request.getParameter("id");

				//selectinfoメゾットにpathとidを入れ呼び出す
				list = fileDao.selectInfo(id,path);

				request.setAttribute("deletelist", list);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/sample3.jsp").forward(request, response);
			}else if(ok != null) {

				//ServletContextでgetRealPathを使いファイルを読み込む
				String path = application.getRealPath("/file/sample.csv");

				//idをsample.jspから取得
				String id = request.getParameter("id");

				//deleteメゾットを呼び出す
				fileDao.delete(path,id);
				request.getRequestDispatcher("/fileServlet").forward(request, response);
			}
		}

	}

}
