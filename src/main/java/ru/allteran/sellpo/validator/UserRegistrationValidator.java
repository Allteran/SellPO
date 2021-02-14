package ru.allteran.sellpo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.allteran.sellpo.domain.User;
import ru.allteran.sellpo.service.UserService;

@Component
public class UserRegistrationValidator implements Validator {
    @Autowired
    private UserService userService;

    @Value("${validator.empty}")
    private String emptyMessage;

    @Value("${validator.phone}")
    private String phoneMessage;

    @Value("${validator.phoneExist}")
    private String phoneExistMessage;
    @Value("${validator.passwordConfirm}")
    private String passwordConfirmMessage;
    @Value("${validator.passwordLength}")
    private String passwordLengthMessage;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmpty(errors, "firstName", emptyMessage);
        ValidationUtils.rejectIfEmpty(errors, "lastName", emptyMessage);

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", phoneMessage);
        if (user.getPhone().length() != 11) {
            errors.rejectValue("phone", phoneMessage);
        }
        /**
         * Next line uses regex to check if input string matches with special regex pattern
         * \ - means that it's gonna be regular expresion, so wait for the pattern
         * \^ - means that it will starts from new line
         * ?(79) - means that at the begining gonna be two numbers "79"
         * \d - means that next text is digits
         * {9} - means that it gonna be only 9 number of digits, no more or less
         */
        if (!user.getPhone().matches("\\^?(79)\\d{9}")) {
            errors.rejectValue("phone", phoneMessage);
        }
        if (userService.findByPhone(user.getPhone()) != null) {
            errors.rejectValue("phone", phoneExistMessage);
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", passwordLengthMessage);
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", passwordLengthMessage);
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", emptyMessage);
        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", passwordConfirmMessage);
        }

    }


}
