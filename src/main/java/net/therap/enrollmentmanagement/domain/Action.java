package net.therap.enrollmentmanagement.domain;

/**
 * @author rumi.dipto
 * @since 9/16/21
 */
public enum Action {

    SAVE("save"),
    UPDATE("update"),
    DELETE("delete"),
    VIEW("view"),
    EDIT("edit");

    private String naturalName;

    Action(String naturalName) {
        this.naturalName = naturalName;
    }

    public String getNaturalName() {
        return this.naturalName;
    }

    public static Action getAction(String text) {
        for (Action action : Action.values()) {
            if (action.getNaturalName().equalsIgnoreCase(text)) {
                return action;
            }
        }
        return null;
    }
}
