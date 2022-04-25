package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import dto.FileDto;

public class FileDao {

	//FileInをインスタンス化しテキストファイルのデータを読み込む
	FileIn objIn = new FileIn();
	//FileOutをインスタンス化
	FileOut objOut = new FileOut();

	//isbn番号と本のタイトルを取得
	public List<FileDto>  getList(String path) {

		//Listのインスタンス化
		List<FileDto> list = new ArrayList<>();

		try{
			//ファイルの1行目データを格納用
			String strLine;

			list.clear();
			FileDto fileDto = new FileDto();

			//ファイルをオープンできるか判断する
			objIn.open(path);

			 //ファイルの一行目がnullか比べている
			while((strLine = objIn.readLine()) != null) {
				//ファイルの１行目がisbnから始まる場合スキップしデータ化しない
				if(strLine.startsWith("isbn")) {
					continue;
				}

				//1行目文字を","で区切り、分割回数を"-1"にする ※-1をすると空文字もデータ化になる
				String[] date = strLine.split(",",-1);
				String isbn = date[0];
				String title = date[1];
				String price = date[2];
				String author = date[3];
				String comment = date[4];

				FileDto filedto = new FileDto(isbn,title, Integer.parseInt(price), author, comment);

				list.add(filedto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		objIn.close();
		return list;
	}

	//ISBNの番号を元に詳細データの取得
	public List<FileDto> selectInfo(String id, String path) {
		//ファイルの1行目データを格納用
		String strLine;

		//FileDtoクラスをオブジェクト化
		FileDto fileDto = new FileDto();

		//ListクラスをArrayListにしオブジェクト化
		List<FileDto> list = new ArrayList<>();

		//ファイルをオープンし開けるか判断
		 objIn.open(path);

			//最初の1行目がnullか判断
			while((strLine = objIn.readLine()) != null) {

				//1行目がisbnから始まっていたら処理を飛ばす
				if(strLine.startsWith("isbn")) {
					continue;
				}

				//strLineに入っているデータを","基準にデータを分けている。 ※-1は空文字もデータに入るようにしている
				String[] date = strLine.split(",",-1);

				//データ化した[0]番目がISBNと同じ番号ならデータに追加していく
				if(date[0].equals(id)) {
					String isbn = date[0];
					String title = date[1];
					String price = date[2];
					String author = date[3];
					String comment = date[4];

					//データに追加したのをFileDtoに追加していく
					FileDto filedto = new FileDto(isbn,title, Integer.parseInt(price), author, comment);

					//listにFileDtoに追加したデータをセットしていく
					list.add(filedto);

					//FileInクラスのストリームのクローズメゾットを呼ぶ
					objIn.close();
					break;
				}
			}
		return list;
	}

	//リストに追加するメゾット
	public void writeFile(String path,String isbn, String title, String price, String author, String comment) {
		FileOut objOut = new FileOut();
		FileIn objIn = new FileIn();
		//FileDtoクラスをオブジェクト化
		List<FileDto> list = new ArrayList<>();

		//getListメゾットを呼び出しlistに代入している。
		list = getList(path);

		//FileOutのopenメゾット呼び出し読み込めるか判断している
		objOut.open(path);

		//FileOutのwritelnメゾットを呼び出しファイルへのデータ書き込みを行う
		objOut.writeln("isbn" + "," + "title" + "," + "price" +  ","+  "author" + "," + "comment");

		//FileDtoクラスに全データを格納する
		FileDto filedto = new FileDto(isbn,title,Integer.parseInt(price), author, comment);

		//格納したfileDtoをリストに追加する
		list.add(filedto);

		//listに入っているデータをFileOutのwritelnメゾットを呼び出しファイルへのデータ書き込みを行う
		for (int i = 0; i < list.size(); i++) {
	        filedto = list.get(i);
			objOut.writeln(filedto.getIsbn() + "," + filedto.getTitle() + "," + filedto.getPrice() + ","
							+ filedto.getAuthor() + "," + filedto.getComment());
		}
		//FileOutクラスのcloseメゾットでファイルのクローズを行う
		objOut.close();
	}

	//ISBNを元に一部のデータを削除
	public void delete(String path, String id) {

		//FileDtoクラスをオブジェクト化
		List<FileDto> list = new ArrayList<>();

		String strLine;

		//ファイルをオープンし開けるか判断
		objIn.open(path);

		FileDto filedto = new FileDto();

		//ファイルの最初の1行目がNULLか判断
		while((strLine = objIn.readLine()) != null) {

			//1行目がisbnから始まっていたら処理を飛ばす
			if(strLine.startsWith("isbn")) {
				continue;
			}

			//strLineに入っているデータを","基準にデータを分けている。 ※-1は空文字もデータに入るようにしている
			String[] date = strLine.split(",",-1);

			//データ化した[0]番目が削除対象のISBNと同じ番号以外をデータに追加する
			if(date[0].equals(id)) {
				continue;
			}else{
				String isbn = date[0];
				String title = date[1];
				String price = date[2];
				String author = date[3];
				String comment = date[4];

				//データに追加したのをFileDtoに追加していく
				 filedto = new FileDto(isbn,title, Integer.parseInt(price), author, comment);

				//listにFileDtoを追加したデータをセットしていく
				list.add(filedto);
			}
		}
		//FileOutクラスのcloseメゾットでファイルのクローズを行う
		objIn.close();

		//FileInのopenメゾット呼び出し読み込めるか判断している
		objOut.open(path);

		objOut.writeln("isbn" + "," + "title" + "," + "price" +  ","+  "author" + "," + "comment");

		//ファイルに削除対象以外選択したデータをwritelnメゾットを使い書き込んでいく。
		for (int i = 0; i < list.size(); i++) {
	        filedto = list.get(i);
			objOut.writeln(filedto.getIsbn() + "," + filedto.getTitle() + "," + filedto.getPrice() + ","
							+ filedto.getAuthor() + "," + filedto.getComment());
		}
		//FileOutクラスのcloseメゾットでファイルのクローズを行う
		objOut.close();
	}

	//ISBN又は本の名前を元に検索する
	public List<FileDto> searchList(String name, String path) {
		List<FileDto> list = new ArrayList<FileDto>();

		String strLine;

		FileDto filedto = new FileDto();

		objIn.open(path);


		while((strLine = objIn.readLine()) != null) {

			Pattern pattern = Pattern.compile(name); //検索したい文字列　"う"　を設定する Pattern を作成
		//	Matcher matcher1 = pattern.matcher(name); //検索対象の文字列　"あいうえお"　を検索対象にするよっていう matcher　を作成

			//1行目がisbnから始まっていたら処理を飛ばす
			if(strLine.startsWith("isbn")) {
				continue;
			}

			String[] date = strLine.split(",",-1);

			if (pattern.matcher(date[1]).find()){
				String isbn = date[0];
				String title = date[1];
				String price = date[2];
				String author = date[3];
				String comment = date[4];

				//データに追加したのをFileDtoに追加していく
				FileDto fileDto = new FileDto(isbn,title, Integer.parseInt(price), author, comment);

				//listにFileDtoに追加したデータをセットしていく
				list.add(fileDto);



			}else {
				continue;
			}
		 }
		//FileInクラスのストリームのクローズメゾットを呼ぶ
		objIn.close();
		return list;
	}

	//書籍の変更を行えるメゾット
	public void edit(String path,String isbn, String title, String price, String author, String comment) {
		//ファイルの1行目データを格納用
		String strLine;

		//FileDtoクラスをオブジェクト化
		FileDto fileDto = new FileDto();

		//Listをオブジェクト化
		List<FileDto> list = new ArrayList<FileDto>();

		//getListメゾットを呼び出しlistに代入している
		list = getList(path);

		//ファイルをオープンし開けるか判断
		objIn.open(path);

		//FileInクラスでcsvファイル1行目がnullかどうか判断している
		while((strLine = objIn.readLine()) != null) {
			//1行目がisbnから始まるか判断している
			if(strLine.startsWith("isbn")) {
				continue;
			//また1行目がservletから取得したisbnから始まるか判断している
			}else if(strLine.startsWith(isbn)) {
				//文字列を","で区切り配列のデータ化している
				String[] date = strLine.split(",",-1);
				//最初に入っていたデータにServletから取得しているそれぞれのデータを上書きしている
				date[0] = isbn;
				date[1] = title;
				date[2] = price;
				date[3] = author;
				date[4] = comment;

				//上書きしたデータをfiledtoオブジェクトに格納する
				 FileDto filedto = new FileDto(isbn,title, Integer.parseInt(price), author, comment);

				for (int i = 0; i < list.size(); i++) {
					//すべてのデータを格納するfileDtoにlist.get()を代入する
			        fileDto = list.get(i);
			        //各リストのISBN番号がServletから取得したisbnと同じ値か判断
			        if(fileDto.getIsbn().equals(isbn)) {
			        	//一致したi番号の要素に上書きしたfiledtoをリストにセットする
			        	list.set(i, filedto);
			        }
				}

				//FileOutクラスのcloseメゾットでファイルのクローズを行う
				objIn.close();

				objOut.open(path);

				//FileOutのwritelnメゾットを呼び出しファイルへのデータ書き込みを行う
				objOut.writeln("isbn" + "," + "title" + "," + "price" +  ","+  "author" + "," + "comment");

				//listにセットしたデータをFileOutのwritelnメゾットを呼び出しファイルへのデータ書き込みを行う
				for (int i = 0; i < list.size(); i++) {
			        fileDto = list.get(i);
					objOut.writeln(fileDto.getIsbn() + "," + fileDto.getTitle() + "," + fileDto.getPrice() + ","
									+ fileDto.getAuthor() + "," + fileDto.getComment());
				}
				//FileOutクラスのcloseメゾットでファイルのクローズを行う
				objOut.close();
				}
		}
	}
}