package algo.models;

import javax.persistence.*;

/**
 * Created by AzatYusupov on 26.12.2017.
 */
@Entity
@Table(name = "problem")
public class Problem {
    public static final int PEAR_PAGE = 20;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;

    String title;
    String body;
    long timeLimit;
    long memoryLimit;

    @OneToOne
    @JoinColumn(name = "id")
    ProblemData data;

    @Transient
    int solvedByUser;

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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(long timeLimit) {
        this.timeLimit = timeLimit;
    }

    public long getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(long memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public ProblemData getData() {
        return data;
    }

    public void setData(ProblemData data) {
        this.data = data;
    }

    public int getSolvedByUser() {
        return solvedByUser;
    }

    public void setSolvedByUser(int solvedByUser) {
        this.solvedByUser = solvedByUser;
    }
}
