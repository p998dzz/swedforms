package lt.swedforms.Entities;
import org.springframework.data.annotation.Id;
/**
 * Created by Super on 2/19/2016.
 */
public class User {

    @Id
    private String id;

    private String email;
    private String pass;
    private String ip;
    private String random;

    public User() {}

    public User(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public void setRandom(String ip, String random)
    {
        this.random = random;
        this.ip = ip;
    }
}