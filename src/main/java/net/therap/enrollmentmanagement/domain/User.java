package net.therap.enrollmentmanagement.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

/**
 * @author rumi.dipto
 * @since 8/9/21
 */
@Entity
@Table(name = "user")
public class User extends Persistent {

    private static final long serialVersionUID = 1L;

    @Column(name = "name")
    @Size(min = 2, max = 100, message = "{validation.length.msg}")
    @NotNull(message = "{validation.notNull.msg}")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @NotNull(message = "{validation.notNull.role.msg}")
    private Role role;

    @Column(name = "user_name")
    @Size(min = 2, max = 100, message = "{validation.length.msg}")
    @NotNull(message = "{validation.notNull.msg}")
    private String userName;

    @Column(name = "password")
    @Size(min = 2, max = 45, message = "{validation.length.msg}")
    @NotNull(message = "{validation.notNull.msg}")
    private String password;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.REMOVE)
    private List<Enrollment> enrollmentList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }
}