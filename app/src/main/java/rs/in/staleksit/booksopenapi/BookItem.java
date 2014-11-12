package rs.in.staleksit.booksopenapi;

/**
 * Created by astoisavljevic on 12.11.14..
 */
public class BookItem {

    //~ private fields
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private String isbn;

    private BookItem() {
        // default constructor
    }

    public BookItem(Long id, String title, String description, String imageUrl, String isbn) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.isbn = isbn;
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


}
