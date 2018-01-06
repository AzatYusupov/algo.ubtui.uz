package algo.models;

import org.springframework.security.access.annotation.Secured;

import javax.persistence.*;

/**
 * Created by AzatYusupov on 29.12.2017.
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String username;
    String password;

    @Transient
    String passwordConfirm;

    String title = "";
    String email;
    String code = "";
    String loginIp = "";

    long unId = 0;
    long fkId = 0;
    long grId = 0;
    long lastVisit = 0;
    long roleId = 0;
    String avatar = "";
    String langId = "";

    public User(long id) {
        this.id = id;
    }
    public User(){}

    @OneToOne(mappedBy = "user")
    UserData data;
//    @ManyToOne
//    @JoinColumn(name = "roleId", insertable = false, updatable = false)
//    Role role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public long getUnId() {
        return unId;
    }

    public void setUnId(long unId) {
        this.unId = unId;
    }

    public long getFkId() {
        return fkId;
    }

    public void setFkId(long fkId) {
        this.fkId = fkId;
    }

    public long getGrId() {
        return grId;
    }

    public void setGrId(long grId) {
        this.grId = grId;
    }

    public long getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(long lastVisit) {
        this.lastVisit = lastVisit;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLangId() {
        return langId;
    }

    public void setLangId(String langId) {
        this.langId = langId;
    }

//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }
}
