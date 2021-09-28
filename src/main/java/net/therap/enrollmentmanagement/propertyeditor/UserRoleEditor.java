package net.therap.enrollmentmanagement.propertyeditor;

import net.therap.enrollmentmanagement.domain.Role;

import java.beans.PropertyEditorSupport;
import java.util.Objects;

/**
 * @author rumi.dipto
 * @since 9/28/21
 */
public class UserRoleEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String role) throws IllegalArgumentException {
        if (Objects.nonNull(role)) {
            setValue(Role.valueOf(role.toUpperCase()));
        }
    }
}
