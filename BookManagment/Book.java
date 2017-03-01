
public class Book {
	

	private int ISBN_Number;
	private String authorFirstName;
	private String authorLastName;
	private String title;
	private  String yearPublished;
	private String publisher;
	private int quantity;
	private String quality;
	
	public Book(){
		}
	
	public Book(int iSBN_Number, String authorFirstName, String authorLastName, String title, String yearPublished,
			String publisher, int quantity, String quality) {
		super();
		ISBN_Number = iSBN_Number;
		this.authorFirstName = authorFirstName;
		this.authorLastName = authorLastName;
		this.title = title;
		this.yearPublished = yearPublished;
		this.publisher = publisher;
		this.quantity = quantity;
		this.quality = quality;
	}
	public int getISBN_Number() {
		return ISBN_Number;
	}
	public void setISBN_Number(int iSBN_Number) {
		ISBN_Number = iSBN_Number;
	}
	public String getAuthorFirstName() {
		return authorFirstName;
	}
	public void setAuthorFirstName(String authorFirstName) {
		this.authorFirstName = authorFirstName;
	}
	public String getAuthorLastName() {
		return authorLastName;
	}
	public void setAuthorLastName(String authorLastName) {
		this.authorLastName = authorLastName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getYearPublished() {
		return yearPublished;
	}
	public void setYearPublished(String yearPublished) {
		this.yearPublished = yearPublished;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
		
}
