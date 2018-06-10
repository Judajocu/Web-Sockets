package Classes;

public class Tag {

    private long id;
    private Tag Ring;

    public Tag(long id, Tag ring) {
        this.id = id;
        Ring = ring;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Tag getRing() {
        return Ring;
    }

    public void setRing(Tag ring) {
        Ring = ring;
    }
}
