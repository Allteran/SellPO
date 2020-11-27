package ru.allteran.sellpo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.allteran.sellpo.domain.Role;
import ru.allteran.sellpo.domain.User;
import ru.allteran.sellpo.repo.UserRepository;
import ru.allteran.sellpo.service.UserService;
import ru.allteran.sellpo.service.UserValidator;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String userRegistration(User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getFieldErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }
        userService.save(userForm);
        return "redirect:/login";
    }
}
