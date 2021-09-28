package net.therap.enrollmentmanagement.domain;

/**
 * @author rumi.dipto
 * @since 9/7/21
 */
public enum Role {

    ADMIN("admin"),
    USER("user");

    private String naturalName;

    Role(String naturalName) {
        this.naturalName = naturalName;
    }

    public String getNaturalName() {
        return this.naturalName;
    }
}
