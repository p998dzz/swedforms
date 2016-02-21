package lt.swedforms.webServices;

import lt.swedforms.Entities.*;
import lt.swedforms.repositories.ContactUsRepository;
import lt.swedforms.repositories.RegistrationRepository;
import lt.swedforms.repositories.UserRepository;
import lt.swedforms.transferObjects.*;
import lt.swedforms.transferObjects.ContactUs;
import lt.swedforms.transferObjects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Super on 2/17/2016.
 */
@RestController
@SpringBootApplication
public class WebService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RegistrationRepository registrationrepository;
    @Autowired
    private ContactUsRepository contactUsRepository;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public String authenticate(@RequestBody final User person, HttpServletRequest request) {
        List<lt.swedforms.Entities.User> authenticatedUsers = userRepository.findByEmailAndPassword(person.getEmail(), person.getPassword());
        if (userRepository.findByEmailAndPassword(person.getEmail(), person.getPassword()).size() != 0) {
            lt.swedforms.Entities.User user = authenticatedUsers.get(0);
            String userIdentification = setRandom(user, request.getRemoteAddr());
            userRepository.save(user);
            return userIdentification;
        }
        return null;
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String createUser(@RequestBody final User person, HttpServletRequest request) {
        if (userRepository.findByEmail(person.getEmail()).size() == 0) {
            lt.swedforms.Entities.User user = new lt.swedforms.Entities.User(person.getEmail(), person.getPassword());
            String userIdentification = setRandom(user, request.getRemoteAddr());
            userRepository.save(user);
            return userIdentification;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/signOut", method = RequestMethod.POST)
    public String signOut(@RequestBody final UserData randomNumber, HttpServletRequest request) {
        List<lt.swedforms.Entities.User> users = userRepository.findByRandom(randomNumber.getUser());
        if (users.size() != 0) {
            lt.swedforms.Entities.User user = users.get(0);
            user.setRandom("", "");
            userRepository.save(user);
            return "OK";
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/getRegistrations", method = RequestMethod.POST)
    public List<Registration> getRegistrations(@RequestBody final UserData randomNumber, HttpServletRequest request) {
        List<lt.swedforms.Entities.User> users = userRepository.findByRandom(randomNumber.getUser());
        if (users.size() != 0 && users.get(0).getIp() == request.getRemoteAddr()) {
            return registrationrepository.findByUser(users.get(0));
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/createRegistration", method = RequestMethod.POST)
    public String createRegistration(@RequestBody final NewRegistration newRegistration, HttpServletRequest request) {
        if(findUser(newRegistration.getUser(), request) != null) {
            Date date = parseDate(newRegistration);
            Registration registration = new Registration(
                    findUser(newRegistration.getUser(), request),
                    date,
                    newRegistration.getUnit(),
                    newRegistration.getTopic(),
                    newRegistration.getPhoneNumber(),
                    newRegistration.getComment(),
                    newRegistration.getName(),
                    newRegistration.getLastName());
            registrationrepository.save(registration);
            return "OK";
        }
        return null;
    }


    @RequestMapping(value = "/checkRegistration", method = RequestMethod.POST)
    public String checkRegistration(@RequestBody final NewRegistration newRegistration, HttpServletRequest request) {
        if(findUser(newRegistration.getUser(), request) != null) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = new Date();
            try {
                date = format.parse(newRegistration.getDate());
            } catch (ParseException e) {
                return null;
            }
            List<Registration> existingRegistrationOnDate = registrationrepository.findByDate(date);
            if (checkIfDateFree(date, existingRegistrationOnDate)) return null;
            return "OK";
        }
        return null;
    }

    private boolean checkIfDateFree(Date date, List<Registration> existingRegistrationOnDate) {
        for (Registration reg : existingRegistrationOnDate) {
            if (reg.getDate().equals(date))
                return true;
        }
        return false;
    }

    @RequestMapping(value = "/getDataForRegistration", method = RequestMethod.POST)
    public List<DateObject> getDataForRegistration(@RequestBody final UserData person, HttpServletRequest request) {
        if (findUser(person.getUser(),request) != null) {
            return getPosibleDates();
        }
        return null;
    }

    @RequestMapping(value = "/createContactUs", method = RequestMethod.POST)
    public String createContactUs(@RequestBody final ContactUs newContacting) {
        if (newContacting.getUser() != null) {

            return "OK";
        }
        return null;
    }

    //data preparation methods

    private String setRandom(lt.swedforms.Entities.User user, String ip) {
        Random rand = new Random();
        long id = rand.nextLong();
        while (id < 0)
            id = rand.nextLong();
        user.setRandom(ip, id + "");
        return id + "";
    }

    private List<DateObject> getPosibleDates() {
        List<DateObject> possibleDates = new ArrayList<DateObject>();
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        for (int i = 0; i < 30; i++) {
            c.add(Calendar.DATE, 1);
            possibleDates.add(new DateObject(c.getTime()));
        }
        return getDateObjects(possibleDates, c);
    }

    private List<DateObject> getDateObjects(List<DateObject> possibleDates, Calendar c) {
        for (DateObject dateInList : possibleDates) {
            List<Registration> registrations = registrationrepository.findByDate(dateInList.getDate());
            if (registrations.size() != 0) {
                for (Registration registration : registrations) {
                    c.setTime(registration.getDate());
                    dateInList.removeTime(c.get(Calendar.HOUR_OF_DAY) + ":00");
                }
            }
        }
        return possibleDates;
    }

    private Date parseDate(NewRegistration newRegistration){
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return format.parse(newRegistration.getDate());
        } catch (ParseException ex) {
            return null;
        }
    }

    private lt.swedforms.Entities.User findUser(String userId, HttpServletRequest request)
    {
        List<lt.swedforms.Entities.User> users = userRepository.findByRandom(userId);
        if(users.size() != 0 && users.get(0).getIp() == request.getRemoteAddr())
            return users.get(0);
        return null;
    }
}
