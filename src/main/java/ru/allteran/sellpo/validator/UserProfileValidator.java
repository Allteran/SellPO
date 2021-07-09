package ru.allteran.sellpo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.allteran.sellpo.models.User;
import ru.allteran.sellpo.service.UserService;

@Component
public class UserProfileValidator implements Validator {
    @Autowired
    private UserService userService;

    @Value("${validator.passwordConfirm}")
    private String passwordConfirmMessage;

    @Value("${validator.passwordLength}")
    private String passwordLengthMessage;

    @Value("${validator.phoneExist}")
    private String phoneExistMessage;

    @Value("${validator.phone}")
    private String phoneMessage;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        if (!StringUtils.isEmpty(user.getPhone())) {
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
                errors.rejectValue("phone", "wrongFormOrSize");
            }
            if (userService.findByPhone(user.getPhone()) != null) {
                errors.rejectValue("phone", "phoneExistWow");
            }
        }

        if (!StringUtils.isEmpty(user.getPassword())) {
            if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
                errors.rejectValue("password", passwordLengthMessage);
            }

            if (!user.getPassword().equals(user.getPasswordConfirm())) {
                errors.rejectValue("passwordConfirm", passwordConfirmMessage);
            }
        }
    }
}
