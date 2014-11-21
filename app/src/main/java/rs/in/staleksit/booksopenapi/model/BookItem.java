package rs.in.staleksit.booksopenapi.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by astoisavljevic on 12.11.14..
 */
public class BookItem {

    //~ private fields
    @SerializedName("ID")
    private Long id;
    @SerializedName("Title")
    private String title;
    @SerializedName("Description")
    private String description;
    @SerializedName("Image")
    private String imageUrl;
    @SerializedName("ISBN")
    private String isbn;
    @SerializedName("SubTitle")
    private String subTitle;
    @SerializedName("Author")
    private String author;
    @SerializedName("Year")
    private String year;
    @SerializedName("Page")
    private String page;
    @SerializedName("Publisher")
    private String publisher;
    @SerializedName("Download")
    private String downloadUrl;

    // default constructor
    public BookItem() {
        // default constructor
    }

    // search books - bookItem constructor
    public BookItem(Long id, String title, String description, String imageUrl, String isbn) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.isbn = isbn;
    }

    // bookItem - constructor
    public BookItem(Long id, String title, String description, String imageUrl, String isbn,
        String subTitle, String author, String year, String page, String publisher, String downloadUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.isbn = isbn;

        this.subTitle = subTitle;
        this.author = author;
        this.year = year;
        this.page = page;
        this.publisher = publisher;
        this.downloadUrl = downloadUrl;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BookItem[id=");
        sb.append(getId());
        sb.append("; title=");
        sb.append(getTitle());
        sb.append("; isbn=");
        sb.append(getIsbn());
        sb.append("]");
        return sb.toString();
    }
}
