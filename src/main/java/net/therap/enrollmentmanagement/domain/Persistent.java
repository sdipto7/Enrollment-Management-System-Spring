package net.therap.enrollmentmanagement.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author rumi.dipto
 * @since 9/14/21
 */
@MappedSuperclass
public class Persistent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    @NotNull
    protected Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated")
    protected Date updated;

    @PrePersist
    private void onCreate() {
        updated = created = new Date();
    }

    @PreUpdate
    private void onUpdate() {
        updated = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public boolean isNew() {
        return getId() == 0;
    }
}
