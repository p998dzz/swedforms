package lt.swedforms.webServices;

import lt.swedforms.Controllers.DataPreparer;
import lt.swedforms.Controllers.Finder;
import lt.swedforms.db.Write;
import lt.swedforms.transferObjects.Login;
import lt.swedforms.transferObjects.UserData;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Super on 2/17/2016.
 */
@RestController
@SpringBootApplication
public class WebService {

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public String authenticate(@RequestBody final Login person) {
        String user = Finder.FindPerson(person);
        return user;
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String createUser(@RequestBody final Login person) {
        boolean user = Write.newUserRegistration(person.getEmail(), person.getPass());

        return "fsdf";
    }

    @RequestMapping(value = "/getDataForContacting", method = RequestMethod.POST)
    public List<List<String>> getDataForContacting(@RequestBody final UserData person) {
        if(person.getUser() != null)
        {
            return DataPreparer.prepareForContacting();
        }
        return null;
    }

    @RequestMapping(value = "/getDataForRegistration", method = RequestMethod.POST)
    public List<List<String>> getDataForRegistration(@RequestBody final UserData person) {
        if(person.getUser() != null)
        {
            return DataPreparer.prepareForRegistration();
        }
        return null;
    }

}
