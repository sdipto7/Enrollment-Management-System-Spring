package net.therap.enrollmentmanagement.command;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author rumi.dipto
 * @since 9/7/21
 */
public class CredentialCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "{validation.notNull.msg}")
    private String userName;

    @NotNull(message = "{validation.notNull.msg}")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
