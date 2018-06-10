package Classes;

public class Comment {

    private long id;
    private String comment;
    private User author;
    private Product product;

    public Comment(long id, String comment, User author, Product product) {
        this.id = id;
        this.comment = comment;
        this.author = author;
        this.product = product;
    }

    public Comment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
