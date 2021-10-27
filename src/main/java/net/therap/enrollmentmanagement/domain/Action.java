package net.therap.enrollmentmanagement.domain;

/**
 * @author rumi.dipto
 * @since 9/16/21
 */
public enum Action {

    SAVE("SAVE"),
    DELETE("DELETE"),
    VIEW("VIEW");

    private String naturalName;

    Action(String naturalName) {
        this.naturalName = naturalName;
    }

    public String getNaturalName() {
        return this.naturalName;
    }

    public static Action getAction(String text) {
        return valueOf(text.toUpperCase());
    }
}
