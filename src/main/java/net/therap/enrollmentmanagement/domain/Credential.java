package net.therap.enrollmentmanagement.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author rumi.dipto
 * @since 9/7/21
 */
@Entity
@Table(name = "credential")
public class Credential extends Persistent {

    private static final long serialVersionUID = 1L;

    @Column(name = "user_name")
    @Size(min = 2, max = 100)
    @NotNull
    private String userName;

    @Column(name = "password")
    @Size(min = 2, max = 45)
    @NotNull
    private String password;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
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
