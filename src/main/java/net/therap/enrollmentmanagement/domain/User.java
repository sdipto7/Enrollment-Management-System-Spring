package net.therap.enrollmentmanagement.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author rumi.dipto
 * @since 8/9/21
 */
@Entity
@Table(name = "user")
public class User extends Persistent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @OneToOne(mappedBy = "user")
    private Credential credential;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY,
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

    public void setRole(Role role) {
        this.role = role;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    @Override
    public boolean equals(Object object) {
        if (Objects.isNull(object) || !(object instanceof User)) {
            return false;
        }
        User that = (User) object;

        return this.getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }
}