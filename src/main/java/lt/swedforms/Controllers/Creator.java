package lt.swedforms.Controllers;

import lt.swedforms.transferObjects.Login;

package lt.swedforms.transferObjects;

import lt.swedforms.transferObjects.Login;

/**
 * Created by Super on 2/17/2016.
 */
public class Creator {
    public static String createUser(Login person) {
        User user = new User(person.getEmail(), person.getPass());
        UserEntity.addUser(user);
        return Finder.FindPerson(person);
    }
}
