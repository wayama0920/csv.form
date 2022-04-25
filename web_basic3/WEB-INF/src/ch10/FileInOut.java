package ch10;

import dao.FileIn;
import dao.FileOut;

public class FileInOut {

	public static void main (String[] args) {

		String strLine = null;

		FileIn in = new FileIn();
		FileOut out = new FileOut();

		if(out.open("sample.txt") == false) {
			System.exit(1);
		}
		out.writeln("Java基礎");
		out.writeln("ファイル入出力の練習問題を実施中");

 		if(out.close() == false) {
 			System.exit(2);
 		}

 		if(in.open("file/sample.csv") == false) {
 			System.exit(3);
 		}

 		while((strLine = in.readLine()) != null) {
 			System.out.println(strLine);
 		}

 		if(in.close() == false) {
 			System.exit(4);
 		}
	}
}
