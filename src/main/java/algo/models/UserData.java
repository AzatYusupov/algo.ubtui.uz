package algo.models;

import javax.persistence.*;

/**
 * Created by AzatYusupov on 02.01.2018.
 */
@Entity
@Table(name = "userdata")
public class UserData {

    public static final int PEAR_PAGE = 20;
    public static final int PROBLEMS_PEAR_ON_PROFILE = 25;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    long solved;
    String solvedData;
    String unsolvedData;
    long lastAccept;

    @Transient
    String lastAcceptDate;

    @Transient
    int place;

    @OneToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSolved() {
        return solved;
    }

    public void setSolved(long solved) {
        this.solved = solved;
    }

    public String getSolvedData() {
        return solvedData;
    }

    public void setSolvedData(String solvedData) {
        this.solvedData = solvedData;
    }

    public String getUnsolvedData() {
        return unsolvedData;
    }

    public void setUnsolvedData(String unsolvedData) {
        this.unsolvedData = unsolvedData;
    }

    public long getLastAccept() {
        return lastAccept;
    }

    public void setLastAccept(long lastAccept) {
        this.lastAccept = lastAccept;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLastAcceptDate() {
        return lastAcceptDate;
    }

    public void setLastAcceptDate(String lastAcceptDate) {
        this.lastAcceptDate = lastAcceptDate;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}
