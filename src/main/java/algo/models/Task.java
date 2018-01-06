package algo.models;

import javax.persistence.*;

/**
 * Created by AzatYusupov on 28.12.2017.
 */
@Entity
@Table(name = "task")
public class Task {

    public static final int PEAR_PAGE = 30;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String problemId;
    String source;
    String fileName;

    long createdOn;
    long createdTime;
    int testCase = 0;
    long contestId = 0;
    int size;
    long time;
    long memory;
    String error;

    @Transient
    String date;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    User user;

    @ManyToOne
    @JoinColumn(name = "LANG_ID")
    Lang lang;

    @ManyToOne
    @JoinColumn(name = "STATE")
    State state;

    public User getUser() {
        user.setPassword("");
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public int getTestCase() {
        return testCase;
    }

    public void setTestCase(int testCase) {
        this.testCase = testCase;
    }

    public long getContestId() {
        return contestId;
    }

    public void setContestId(long contestId) {
        this.contestId = contestId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getMemory() {
        return memory;
    }

    public void setMemory(long memory) {
        this.memory = memory;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Lang getLang() {
        return lang;
    }

    public void setLang(Lang lang) {
        this.lang = lang;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
