package net.therap.enrollmentmanagement.editor;

import net.therap.enrollmentmanagement.domain.Action;

import java.beans.PropertyEditorSupport;
import java.util.Objects;

/**
 * @author rumi.dipto
 * @since 10/4/21
 */
public class ActionEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String action) {
        if (Objects.nonNull(action)) {
            setValue(Action.getAction(action));
        }
    }
}
