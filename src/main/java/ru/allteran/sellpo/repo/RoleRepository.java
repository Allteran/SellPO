package ru.allteran.sellpo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.allteran.sellpo.domain.Role;

import java.util.List;

@Repository
public interface RoleRepository extends MongoRepository<Role, Long> {
    List<Role> findAll();

    Role findFirstById(long id);
}
