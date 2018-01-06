package algo.models;

import javax.persistence.*;

/**
 * Created by AzatYusupov on 26.12.2017.
 */
@Entity
@Table(name = "problemdata")
public class ProblemData {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROBLEM_ID")
    String problemId;

    long accepted;
    long attempts;

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    public long getAccepted() {
        return accepted;
    }

    public void setAccepted(long accepted) {
        this.accepted = accepted;
    }

    public long getAttempts() {
        return attempts;
    }

    public void setAttempts(long attempts) {
        this.attempts = attempts;
    }
}
