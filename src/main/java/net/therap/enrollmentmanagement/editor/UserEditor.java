package net.therap.enrollmentmanagement.editor;

import net.therap.enrollmentmanagement.domain.User;

import java.beans.PropertyEditorSupport;
import java.util.Objects;

/**
 * @author rumi.dipto
 * @since 9/28/21
 */
public class UserEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String id) {
        if (Objects.nonNull(id)) {
            User user = new User();
            user.setId(Long.parseLong(id));
            setValue(user);
        }
    }
}
