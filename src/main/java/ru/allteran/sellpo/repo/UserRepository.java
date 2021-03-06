package ru.allteran.sellpo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.allteran.sellpo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByPhone(String phone);
}

