package lt.swedforms.transferObjects;

import java.io.Serializable;

/**
 * Created by p998ugh on 2016.02.19.
 */
public class User  implements Serializable {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
