package lt.swedforms.webServices;

import lt.swedforms.Controllers.DataPreparer;
import lt.swedforms.db.Check;
import lt.swedforms.db.Write;
import lt.swedforms.transferObjects.Login;
import lt.swedforms.transferObjects.Registration;
import lt.swedforms.transferObjects.UserData;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Random;

/**
 * Created by Super on 2/17/2016.
 */
@RestController
@SpringBootApplication
public class WebService {

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public String authenticate(@RequestBody final Login person, HttpServletRequest request) {
        if(person.getPass().equals(Check.checkPassword(person.getEmail())))
        {
            String ip = request.getRemoteAddr();
            Random rand = new Random();
            String userIdentification = "u";
            for(int i = 0; i < 6; i++)
            {
                userIdentification+=rand.nextLong();
            }
            //Write.UpdateRandom(person.getEmail(), ip, userIdentification);
            return userIdentification;
        }
        return "null";
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String createUser(@RequestBody final Login person) {
        //boolean user = Write.newUserRegistration(person.getEmail(), person.getPass());
        return "ffsdf";
    }

    @RequestMapping(value = "/createRegistration", method = RequestMethod.POST)
    public String createRegistration(@RequestBody final Registration newRegistration) {
        return "nothing at all";
    }

    @RequestMapping(value = "/checkRegistration", method = RequestMethod.POST)
    public String checkRegistration(@RequestBody final Registration newRegistration) {
       /* List<String[]> registrationRaw = Check.checkRegistrations(Check.checkEandom(person.getUser()));
        List<Registration> registrations = DataPreparer.parseRegistrations(registrationRaw);
        for(Registration reg : registrations )
        {
            if(reg.getTime().equals(newRegistration.getTime()) && reg.getDate().equals(newRegistration.getDate()))
                return "DATETIME_DUPLICATE";
        }
        Write.newRegistration("","","","");*/
        return "OK";
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
