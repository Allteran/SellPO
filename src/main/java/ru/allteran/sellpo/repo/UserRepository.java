package ru.allteran.sellpo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.allteran.sellpo.domain.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByPhone(String phone);
}

