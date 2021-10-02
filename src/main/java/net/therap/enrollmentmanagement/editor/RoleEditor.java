package net.therap.enrollmentmanagement.editor;

import net.therap.enrollmentmanagement.domain.Role;

import java.beans.PropertyEditorSupport;
import java.util.Objects;

/**
 * @author rumi.dipto
 * @since 9/28/21
 */
public class RoleEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String role) {
        if (Objects.nonNull(role)) {
            setValue(Role.valueOf(role.toUpperCase()));
        }
    }
}
