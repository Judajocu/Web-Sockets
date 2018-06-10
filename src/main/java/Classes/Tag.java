package Classes;

public class Tag {

    private long id;
    private String tag;

    public Tag(long id, String tag) {
        this.id = id;
        tag = tag;
    }

    public Tag() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTag() { return this.tag; }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
