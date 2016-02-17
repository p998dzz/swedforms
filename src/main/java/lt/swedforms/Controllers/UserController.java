package lt.swedforms.Controllers;

import lt.swedforms.transferObjects.Login;

import java.util.Random;

/**
 * Created by Super on 2/17/2016.
 */
public class UserController {
    public static String FindPerson(Login person){
        if(person.getEmail().length() != 0 && person.getPass().length() != 0)
        {
            Random rand = new Random();
            return rand.nextDouble()+"";
        }
        return null;
    }
}
