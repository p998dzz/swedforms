package lt.swedforms.webServices;

import lt.swedforms.Controllers.UserController;
import lt.swedforms.transferObjects.Login;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Super on 2/17/2016.
 */
@RestController
@SpringBootApplication
public class WebService {

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public String authenticate(@RequestBody final Login person) {
        String user = UserController.FindPerson(person);
        return "Running ...";
    }

}
