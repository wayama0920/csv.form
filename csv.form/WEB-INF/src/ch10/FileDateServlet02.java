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

public class FileDateServlet02 extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  {
		//ファイルの取得、書き込みなど、サーブレット通信するために使用するメソッド
		ServletContext application = this.getServletContext();

		//FileDtoをオブジェクト化
		FileDto fileDto = new FileDto();

		//FileDaoをオブジェクト化
		FileDao fileDao = new FileDao();

		//List<FileDto>に保存されているセッションオブジェクトの中で、listの値を戻り値として返す
		List<FileDto> list =  (List<FileDto>) application.getAttribute("list");

		String message = null;



		try {

			request.setCharacterEncoding("UTF-8");

			//csvファイルを読み込む変数path
			String path = application.getRealPath("/file/sample.csv");

			list =fileDao.getList(path);

			//isbnをjspファイルから取得
			String isbn = request.getParameter("isbn");


			 for(int i = 0; i < list.size(); i++) {
				fileDto = list.get(i);
				while(fileDto.getIsbn().equals(isbn)) {
					message = "※入力したISBN番号は存在しております、別のISBN番号を入力してください。";
					break;
				}
			}

			if(message == null) {
			//titleをjspファイルから取得
			String title = request.getParameter("title");

			//priceをjspファイルから取得
			String price = request.getParameter("price");

			//authorをjspファイルから取得
			String author = request.getParameter("author");

			//commentをjspファイルから取得
			String comment = request.getParameter("comment");



			//writeFileメゾットを呼び出し新しいデータを登録しファイルに足している
			fileDao.writeFile(path,isbn,title,price,author,comment);
			}

		}catch(Exception e) {

		}finally {
			if(message != null) {

				request.setAttribute("message", message);
				request.getRequestDispatcher("/view/sample2.jsp").forward(request, response);
			}else {
				request.getRequestDispatcher("/fileServlet").forward(request, response);
			}
		}
	}

}
