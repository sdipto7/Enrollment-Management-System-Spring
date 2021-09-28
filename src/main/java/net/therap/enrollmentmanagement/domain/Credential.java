package net.therap.enrollmentmanagement.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author rumi.dipto
 * @since 9/7/21
 */
@Entity
@Table(name = "credential")
public class Credential extends Persistent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;

    public Credential() {
    }

    public Credential(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
