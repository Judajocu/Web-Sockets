package Classes;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Tag implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
