package ru.allteran.sellpo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.allteran.sellpo.domain.User;
import ru.allteran.sellpo.domain.Role;
import ru.allteran.sellpo.repo.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByPhone(s);

        if (user == null) {
            throw new UsernameNotFoundException("Phone number" + s + " not found");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }


//        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(Role.ADMIN.name()),
//                new SimpleGrantedAuthority(Role.USER.name()));
        return new org.springframework.security.core.userdetails.User(user.getPhone(), user.getPassword(), grantedAuthorities);
    }
}
