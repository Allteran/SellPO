package ru.allteran.sellpo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.allteran.sellpo.domain.Role;
import ru.allteran.sellpo.domain.User;
import ru.allteran.sellpo.repo.RoleRepository;
import ru.allteran.sellpo.repo.UserRepository;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<Role> defaultRole = new HashSet<>();
        defaultRole.add(roleRepo.findFirstById(Role.ID_USER));

        user.setRoles(defaultRole);
        userRepo.save(user);
    }

    public User findByPhone(String phone) {
        return userRepo.findByPhone(phone);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }


    /**
     * Uncomment next lines only if you are running first time this code on your pc
     * it will fill collection Role with static data
     */

//    private static Set<Role> roles = new HashSet<>();
//
//    static {
//        roles.add(new Role(Role.ID_USER, "user"));
//        roles.add(new Role(Role.ID_ADMIN, "admin"));
//        roles.add(new Role(Role.ID_MANAGER, "manager"));
//    }
//
//    @PostConstruct
//    public void init() {
//        roleRepo.saveAll(roles);
//    }

}
