package ru.allteran.sellpo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.allteran.sellpo.models.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByPhone(String phone);
}

