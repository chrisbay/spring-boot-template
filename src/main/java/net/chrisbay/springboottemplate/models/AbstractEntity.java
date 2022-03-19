package net.chrisbay.springboottemplate.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    public int getId() {
        return id;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        AbstractEntity that = (AbstractEntity) object;
        return id == that.id;
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), id);
    }
}
