package algo.models;

import javax.persistence.*;

/**
 * Created by AzatYusupov on 28.12.2017.
 */
@Entity
@Table(name = "lang")
public class Lang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;

//    @Column(name = "TITLE")
    String title;

    public Lang(){}

    public Lang(String id) {
        this.id= id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
