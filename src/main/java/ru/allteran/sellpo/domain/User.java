package ru.allteran.sellpo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Document
public class User {
    @Id
//    @NotBlank(message = "79XXXXXXXXX")
//    @Size(min = 11, max = 11, message = "79XXXXXXXXX")
    private String phone;

    //    @Size(min = 8, max = 32, message = "Длинна пароля от 8-ми до 32-х символов")
    private String password;
    @Transient
    private String passwordConfirm;

    //    @NotBlank(message = "Заполните имя")
    private String firstName;

    //    @NotBlank(message = "Заполните фамилию")
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

    public String getFullname() {
        return firstName + " " + lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
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
