package rs.in.staleksit.booksopenapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by astoisavljevic on 21.11.14..
 */
public class BookSearch {

    @SerializedName("Error")
    private String error;

    @SerializedName("Time")
    private float time;

    @SerializedName("Total")
    private String total;

    @SerializedName("Page")
    private int page;

    @SerializedName("Books")
    private List<BookItem> books;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<BookItem> getBooks() {
        return books;
    }

    public void setBooks(List<BookItem> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BookSearch [error=");
        sb.append(getError());
        sb.append("; time=");
        sb.append(String.valueOf(getTime()));
        sb.append("; total=");
        sb.append(getTotal());
        sb.append("; page=");
        sb.append(String.valueOf(getPage()));
        sb.append("]");
        return sb.toString();
    }
}
