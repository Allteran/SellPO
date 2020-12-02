package ru.allteran.sellpo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.allteran.sellpo.domain.User;
import ru.allteran.sellpo.service.UserService;
import ru.allteran.sellpo.service.UserValidator;

import java.util.Map;

@Controller
public class RegistrationController {
    public static final String SUCCESS_REGISTRATION_MESSAGE = "Регистрация завершена";
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
    private static final String LOGIN_ERROR_MESSAGE = "Неверное имя пользователя или пароль";

    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String userRegistration(User userForm, BindingResult bindingResult, Model model,
                                   RedirectAttributes redirectAttributes) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getFieldErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }
        userService.save(userForm);


        redirectAttributes.addFlashAttribute("alertMessage", SUCCESS_REGISTRATION_MESSAGE);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model, String error) {
        if (error != null) {
            model.addAttribute("loginError", LOGIN_ERROR_MESSAGE);
        }
        return "login";
    }

}
