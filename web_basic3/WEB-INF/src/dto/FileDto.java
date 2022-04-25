package dto;

public class FileDto {

	private String isbn;			 //書類番号を格納する変数
	private String title;
	private int price;
	private String author;
	private String comment;

	public FileDto() {
		String isbn = null;			 //書類番号を格納する変数
		String title = null;
		int price = 0;
		String author = null;
		String comment = null;

	}
	public FileDto(String isbn, String title,int price,String author,String comment) {
		this.isbn = isbn;
		this.title = title;
		this.setPrice(price);
		this.setAuthor(author);
		this.setComment(comment);
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;

	}

	public String getIsbn() {
		return isbn;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}


}


