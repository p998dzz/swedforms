package lt.swedforms.Controllers;

import lt.swedforms.transferObjects.User;

import java.util.Random;

/**
 * Created by Super on 2/17/2016.
 */
public class Finder {
    public static String FindPerson(User person){
        if(person.getEmail().length() != 0 && person.getPassword().length() != 0)
        {
            Random rand = new Random();
            return rand.nextDouble()+"";
        }
        return null;
    }

    public static String checkPersonExistence(User person) {
        if(person.getEmail().length() != 0 && person.getPassword().length() != 0)
        {
            Random rand = new Random();
            return rand.nextDouble()+"";
        }
        return 200+"";
    }
}
