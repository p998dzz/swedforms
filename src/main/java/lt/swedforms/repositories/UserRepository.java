package lt.swedforms.repositories;

import lt.swedforms.Entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Super on 2/19/2016.
 */
public interface UserRepository extends MongoRepository<User, String> {
    public User findByRandom(String random);
    public User findByEmail(String email);
}
