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
    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 100, message = "Length of name must be between {min} to {max}")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @NotNull(message = "You must select a role for the user")
    private Role role;

    @OneToOne(mappedBy = "user")
    private Credential credential;

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