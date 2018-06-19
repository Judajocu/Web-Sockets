package Classes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Product implements Serializable {

    @Id
    private long id;
    private String Title;
    private String Body;
    @ManyToOne
    private User Author;
    private Date DateTime;
    @ManyToMany
    private List<Comment> comments;
    @ManyToMany
    private List<Tag> tags;

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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
