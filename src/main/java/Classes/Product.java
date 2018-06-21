package Classes;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({@NamedQuery(name = "Product.findAllById", query = "select p from Product  p ORDER BY p.id DESC ")})
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String Title;
    private String Body;
    private Long likes=0L;
    @OneToOne()
    private User Author;
    private Date DateTime;
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Comment> comments;
    @ManyToMany(fetch = FetchType.EAGER)
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

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public void plus() {
        this.likes++;
    }
    public void minus() {
        this.likes--;
    }
}
