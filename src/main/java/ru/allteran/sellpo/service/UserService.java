package ru.allteran.sellpo.service;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.allteran.sellpo.domain.Dealer;
import ru.allteran.sellpo.domain.Role;
import ru.allteran.sellpo.domain.User;
import ru.allteran.sellpo.repo.DealerRepository;
import ru.allteran.sellpo.repo.RoleRepository;
import ru.allteran.sellpo.repo.UserRepository;

import java.util.*;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DealerRepository dealerRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepo.findByPhone(s);

        if (user == null) {
            throw new UsernameNotFoundException("Phone number" + s + " not found");
        }
        return user;
    }

    public void addUser(User user, Map<String, String> form) {
        user.setId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<Role> defaultRole = new HashSet<>();
        defaultRole.add(roleRepo.findFirstById(Role.ID_USER));

        user.setRoles(defaultRole);

        Dealer defaultDealer = new Dealer(Dealer.ID_DEFAULT_DEALER, Dealer.NAME_DEFAULT_DEALER);
        for (String dealerId : form.values()) {
            Dealer dealer = dealerRepo.findFirstById(dealerId);
            if (dealer != null) {
                user.setDealer(dealer);
                break;
            } else {
                user.setDealer(defaultDealer);
            }
        }

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
    public void saveUser(User editedUser, User userConfirm, Map<String, String> form) {
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
        } else {
            editedUser.setPassword(passwordEncoder.encode(editedUser.getPassword()));
        }

        if (editedUser.getRoles() == null) {
            editedUser.setRoles(userConfirm.getRoles());
        }
        editedUser.getRoles().clear();
        Set<Role> rolesFromDb = new HashSet<>(roleRepo.findAll());

        for (String key : form.keySet()) {
            Role keyRole = roleRepo.findByName(key);
            if (keyRole != null) {
                for (Role roleFromDB : rolesFromDb) {
                    if (roleFromDB.getId() == keyRole.getId()) {
                        editedUser.getRoles().add(keyRole);
                    }
                }
            }
        }

        editedUser.setActive(false);
        for (String key : form.keySet()) {
            if (key.equals("radioActive")) {
                editedUser.setActive(true);
            }
        }

        for (String dealerId : form.values()) {
            Dealer dealer = dealerRepo.findFirstById(dealerId);
            if (dealer != null) {
                editedUser.setDealer(dealer);
                break;
            }
        }

        userRepo.delete(userConfirm);
        userRepo.save(editedUser);
    }

    public void deleteUser(String phone) {
        User userToDelete = userRepo.findByPhone(phone);
        userRepo.delete(userToDelete);
    }

    public User updateProfile(User userEdit, User userConfirm) {
        BasicDBObject updateFields = new BasicDBObject();
        if (!StringUtils.isEmpty(userEdit.getPhone())) {
            updateFields.append("phone",userEdit.getPhone());
        } else {
            userEdit.setPhone(userConfirm.getPhone());
        }

        if (!StringUtils.isEmpty(userEdit.getPassword())) {
            updateFields.append("password", passwordEncoder.encode(userEdit.getPassword()));
        } else {
            userEdit.setPassword(userConfirm.getPassword());
        }
        BasicDBObject setQuery = new BasicDBObject();
        setQuery.append("$set", updateFields);
        BasicDBObject searchQuery = new BasicDBObject("phone", userConfirm.getPhone());

        MongoDatabase mongoDb = MongoClients.create().getDatabase("sellPO");
        MongoCollection<Document> collection = mongoDb.getCollection("user");
        collection.updateOne(searchQuery,setQuery);

        userEdit.setId(userConfirm.getId());
        userEdit.setDealer(userConfirm.getDealer());
        userEdit.setActive(userConfirm.isActive());
        userEdit.setRoles(userConfirm.getRoles());
        userEdit.setLastName(userConfirm.getLastName());
        userEdit.setFirstName(userConfirm.getFirstName());
        return userEdit;
    }


    /**
     * Uncomment next lines only if you are running first time this code on your pc
     * it will fill collection Role with static data
     */

//    private static Set<Role> roles = new HashSet<>();
//
//    static {
//        roles.add(new Role(Role.ID_USER, "USER"));
//        roles.add(new Role(Role.ID_ADMIN, "ADMIN"));
//        roles.add(new Role(Role.ID_MANAGER, "MANAGER"));
//    }
//
//    @PostConstruct
//    public void init() {
//        roleRepo.deleteAll();
//        roleRepo.saveAll(roles);
//    }

}
