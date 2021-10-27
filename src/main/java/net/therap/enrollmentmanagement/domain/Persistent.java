package net.therap.enrollmentmanagement.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

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
    protected Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated")
    protected Date updated;

    @PrePersist
    private void onCreate() {
        created = new Date();
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

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (Objects.isNull(object) || !(object instanceof Persistent)) {
            return false;
        }
        Persistent that = (Persistent) object;

        return this.getId() == that.getId();
    }
}
