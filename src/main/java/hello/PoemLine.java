package hello;

public class PoemLine {
	
	private String sentence;
	
	private String title;
	
	private String author;
	
	public PoemLine(String sentence, String title, String author) {
		this.sentence = sentence;
		this.title = title;
		this.author = author;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
}
