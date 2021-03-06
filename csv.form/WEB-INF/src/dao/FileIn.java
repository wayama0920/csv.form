
package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * テキストファイルから入力されたデータを読み取ります。
 */
public class FileIn {
	private Scanner sin = null; // Scannerクラス

	/* ファイルのオープンを行うメソッド */
	public boolean open(String fname) {
		boolean sts = true;
		try {
			// 読み込みファイルオープンに相当する処理
			sin = new Scanner(new File(fname));
		} catch (FileNotFoundException e) {
			sts = false;
		}
		return sts;
	}

	/* 読み込んだファイルデータのから1行読み出し処理を行うメソッド */
	public String readLine() {
		String buff;

		// 読み込み可能データがあるか判定
		if (sin.hasNextLine()) {
			buff = sin.nextLine();
		} else {
			buff = null;
		}
		return buff;
	}




	/* ストリームのクローズを行うメソッド */
	public boolean close() {
		boolean sts = true;
		try {
			sin.close(); // ストリームのクローズ
		} catch (Exception e) {
			sts = false;
		}
		return sts;
	}

}

//
//public class FileIn {
//
//	private Scanner sin = null;
//
//	//ファイルのオープンを行うメゾット
//	public boolean open(String fname) {
//		boolean sts = true;
//		try {
//
//			//読み込みファイルオープンに相当する処理
//			sin = new Scanner(new File(fname));
//		}catch(FileNotFoundException e) {
//			System.out.println("ファイル名に誤りがあります。" + e);
//			sts = false;
//		}
//		return sts;
//	}
//
//	//読み込んだファイルデータからの1行読み出し処理を行うメゾット
//	public String readLine() {
//		String buff;
//
//		//読み込み可能データがあるか判定
//		if(sin.hasNextLine()) {
//			buff = sin.nextLine();
//		}else {
//			buff = null;
//		}
//		return buff;
//	}
//
//	//ストリームのクローズを行うメゾット
//	public boolean close() {
//		boolean sts = true;
//		try {
//			sin.close();
//		}catch(Exception e) {
//			System.out.println("ストリームクローズエラー" + e);
//			sts = false;
//		}
//		return sts;
//	}
//
//}
