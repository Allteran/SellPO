package ru.allteran.sellpo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.allteran.sellpo.domain.User;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;


    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmpty(errors, "firstName", "Необходимо заполнить");
        ValidationUtils.rejectIfEmpty(errors, "lastName", "Необходимо заполнить");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "79XXXXXXXXX");
        if (user.getPhone().length() != 11) {
            errors.rejectValue("phone", "79XXXXXXXXX");
        }
        if (userService.findByPhone(user.getPhone()) != null) {
            errors.rejectValue("phone", "Данный номер уже используется");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Длинна пароля от 8-ми до 32-х символов");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Длинна пароля от 8-ми до 32-х символов");
        }
        if(!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Пароли не совпадают");
        }

    }
}
