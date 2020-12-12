package ru.allteran.sellpo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.allteran.sellpo.domain.Role;
import ru.allteran.sellpo.domain.User;
import ru.allteran.sellpo.repo.RoleRepository;
import ru.allteran.sellpo.repo.UserRepository;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void addUser(User user) {
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
     * @param editedUser  - represents User that we edit. It stores all changed data, to compare it and save to DB
     * @param userConfirm - represents User before edit. It stores all incoming data. When we gonna update user in DB
     *                    we should compare editedUser with userConfirm to check if we have changed any of fields
     */
    public void userSave(User editedUser, User userConfirm, Map<String, String> form) {
        if (StringUtils.isEmpty(editedUser.getFirstName())) {
            editedUser.setFirstName(userConfirm.getFirstName());
        }
        if (StringUtils.isEmpty(editedUser.getLastName())) {
            editedUser.setLastName(userConfirm.getLastName());
        }
        if (StringUtils.isEmpty(editedUser.getPhone())) {
            editedUser.setPhone(userConfirm.getPhone());
        }
        if (StringUtils.isEmpty(editedUser.getPassword())) {
            editedUser.setPassword(userConfirm.getPassword());
        }

        if (editedUser.getRoles() == null) {
            editedUser.setRoles(userConfirm.getRoles());
        }
        editedUser.getRoles().clear();
        Set<Role> rolesFromDb = new HashSet<>(roleRepo.findAll());

        for (String key : form.keySet()) {
            System.out.println("Form key is: " + key);
            Role keyRole = roleRepo.findByName(key);
            if (keyRole != null) {
                System.out.println("keyRole not null, keyRole.name = " + keyRole.getName());
                for(Role roleFromDB : rolesFromDb) {
                    if(roleFromDB.getId() == keyRole.getId()) {
                        System.out.println("rolesFromDB contains keyRole with name " + keyRole.getName());
                        editedUser.getRoles().add(keyRole);
                        System.out.println("keyRole added into EditedUser");
                    }
                }
            }
        }

        System.out.println("editedUser roles are: " + editedUser.getRoles().toString());
        if (!editedUser.getPassword().equals(userConfirm.getPhone())) {
            userRepo.delete(userConfirm);
        }
        userRepo.save(editedUser);
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
