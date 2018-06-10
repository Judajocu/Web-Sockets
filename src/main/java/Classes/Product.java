package Classes;

import java.util.ArrayList;
import java.util.Date;

public class Product {
    private long id;
    private String Title;
    private String Body;
    private User Author;
    private Date DateTime;
    private ArrayList<Comment> comments;
    private ArrayList<Tag> tags;

    public Product(long id, String title, String body, User author, Date dateTime, ArrayList<Comment> comments, ArrayList<Tag> tags) {
        this.id = id;
        Title = title;
        Body = body;
        Author = author;
        DateTime = dateTime;
        this.comments = comments;
        this.tags = tags;
    }

    public Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }

    public User getAuthor() {
        return Author;
    }

    public void setAuthor(User author) {
        Author = author;
    }

    public Date getDateTime() {
        return DateTime;
    }

    public void setDateTime(Date dateTime) {
        DateTime = dateTime;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }
}
