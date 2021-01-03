package ru.allteran.sellpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.allteran.sellpo.domain.User;
import ru.allteran.sellpo.repo.DealerRepository;
import ru.allteran.sellpo.service.UserService;
import ru.allteran.sellpo.service.UserRegistrationValidator;

import java.util.Map;

@Controller
public class RegistrationController {
    public static final String SUCCESS_REGISTRATION_MESSAGE = "Регистрация завершена";
    private static final String LOGIN_ERROR_MESSAGE = "Неверное имя пользователя или пароль";
    @Autowired
    private DealerRepository dealerRepo;

    @Autowired
    private UserRegistrationValidator userRegistrationValidator;
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("dealers", dealerRepo.findAll());
        return "registration";
    }

    @PostMapping("/registration")
    public String userRegistration(@RequestParam Map<String, String> form,
                                   User userForm, BindingResult bindingResult, Model model,
                                   RedirectAttributes redirectAttributes) {
        userRegistrationValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getFieldErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("dealers", dealerRepo.findAll());
            return "registration";
        }
        userService.addUser(userForm, form);

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
