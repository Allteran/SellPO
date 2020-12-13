package ru.allteran.sellpo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Document
public class User implements UserDetails {
    @Id
    private String phone;
    private String password;

    @Transient
    private String passwordConfirm;

    private String firstName;

    private String lastName;
    private Dealer dealer;
    private boolean active;

    private Set<Role> roles;

    private EmployeeKPI kpi;

    public User() {
    }

    public boolean isAdmin() {
        for (Role role : roles) {
            if (role.getId() == Role.ID_ADMIN) return true;
        }
        return false;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return phone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        //TODO: rewrite it to return isActive
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public EmployeeKPI getKpi() {
        return kpi;
    }

    public void setKpi(EmployeeKPI kpi) {
        this.kpi = kpi;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "phone=" + phone +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dealer=" + dealer +
                ", active=" + active +
                ", roles=" + roles +
                ", kpi=" + kpi +
                '}';
    }
}
