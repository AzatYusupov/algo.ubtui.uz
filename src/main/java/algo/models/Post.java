package algo.models;

import javax.persistence.*;

/**
 * Created by AzatYusupov on 20.12.2017.
 */

@Entity
@Table(name = "news")
public class Post {

    public static final int PEAR_PAGE = 6;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String title;
    String body;
    String author;
    String date;
    int view;
    String imgUrl;
    int main;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getMain() {
        return main;
    }

    public void setMain(int main) {
        this.main = main;
    }
}
