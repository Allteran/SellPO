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
        /**
         * Next line uses regex to check if input string matches with special regex pattern
         * \ - means that it's gonna be regular expresion, so wait for the pattern
         * \^ - means that it will starts from new line
         * ?(79) - means that at the begining gonna be two numbers "79"
         * \d - means that next text is digits
         * {9} - means that it gonna be only 9 number of digits, no more or less
         */
        if(!user.getPhone().matches("\\^?(79)\\d{9}")) {
            errors.rejectValue("phone", "79XXXXXXXXX");
        }
        if (userService.findByPhone(user.getPhone()) != null) {
            errors.rejectValue("phone", "Данный номер уже используется");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Длинна пароля от 8-ми до 32-х символов");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Длинна пароля от 8-ми до 32-х символов");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "Необходимо заполнить");
        if(!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Пароли не совпадают");
        }

    }


}
