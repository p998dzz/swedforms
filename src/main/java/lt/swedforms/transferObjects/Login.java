package lt.swedforms.transferObjects;

import java.io.Serializable;

/**
 * Created by Super on 2/17/2016.
 */
public class Login implements Serializable {
    private String email;
    private String pass;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
