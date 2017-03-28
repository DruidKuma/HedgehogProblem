package model;

/**
 * Created by Iurii Miedviediev
 *
 * Represents a zone in the AppleGarden with number of apples
 *
 * @author DruidKuma
 * @version 1.0.0
 * @since 3/28/17
 */
public class AppleTree {
    private final String id;
    private final Integer value;

    public AppleTree(String id, Integer value) {
        this.id = id;
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AppleTree other = (AppleTree) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return id;
    }
}