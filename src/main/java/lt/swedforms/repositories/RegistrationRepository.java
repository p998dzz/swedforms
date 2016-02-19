package lt.swedforms.repositories;

import lt.swedforms.Entities.Registration;
import lt.swedforms.Entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by Super on 2/19/2016.
 */
public interface RegistrationRepository extends MongoRepository<Registration, String> {
    public List<lt.swedforms.Entities.Registration> findByDate(Date date);
    public List<lt.swedforms.Entities.Registration> findByUser(User user);
}
