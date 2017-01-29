package source;

public class Article {
	
	// Class members
	private String mTitle;
	private String mUrl;
	private String mImgUrl;

	// Constructor
	public Article(String Title, String Url, String ImgUrl) {
		mTitle = Title;
		mUrl = Url;
		mImgUrl = ImgUrl;
	}
	
	
	// Getters and setters
	public String getmTitle() {
		return mTitle;
	}

	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}

	public String getmUrl() {
		return mUrl;
	}

	public void setmUrl(String mUrl) {
		this.mUrl = mUrl;
	}

	public String getmImgUrl() {
		return mImgUrl;
	}

	public void setmImgUrl(String mImgUrl) {
		this.mImgUrl = mImgUrl;
	}

}
