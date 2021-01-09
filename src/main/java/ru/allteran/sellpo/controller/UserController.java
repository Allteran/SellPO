package ru.allteran.sellpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.allteran.sellpo.domain.Dealer;
import ru.allteran.sellpo.domain.Role;
import ru.allteran.sellpo.domain.User;
import ru.allteran.sellpo.repo.DealerRepository;
import ru.allteran.sellpo.repo.RoleRepository;
import ru.allteran.sellpo.service.UserEditValidator;
import ru.allteran.sellpo.service.UserProfileValidator;
import ru.allteran.sellpo.service.UserService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class UserController {
    private static final String ERROR_USER_MESSAGE = "Что-то пошло не так, попробуйте снова";
    private static final String SUCCESS_SAVING_USER_MESSAGE = "Пользовател успешно изменен";
    private static final String SUCCESS_DELETE_USER_MESSAGE = "Пользователь успешно удален";
    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository rolesRepo;
    @Autowired
    private DealerRepository dealerRepo;

    @Autowired
    private UserEditValidator userEditValidator;
    @Autowired
    private UserProfileValidator profileValidator;

    @GetMapping("/userlist")
    public String userList(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);

        return "userList";
    }

    @GetMapping("/edit/{phone}")
    public String userEditForm(@PathVariable String phone, Model model) {
        Set<Role> roles = new HashSet<>(rolesRepo.findAll());
        List<Dealer> dealers = dealerRepo.findAll();
        User user = userService.findByPhone(phone);
        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        model.addAttribute("dealers", dealers);
        model.addAttribute("userPhone", user.getPhone());
        return "userEdit";
    }

    @PostMapping(value = "/edit", params = {"save"})
    public String userSave(
            @Valid User userForm, @RequestParam("userPhone") String incUserPhone,
            @RequestParam Map<String, String> form, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        userEditValidator.validate(userForm, bindingResult);
        User incUser = userService.findByPhone(incUserPhone);
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getFieldErrors(bindingResult);
            Set<Role> roles = new HashSet<>(rolesRepo.findAll());
            model.addAttribute("roles", roles);
            if (incUser != null) {
                model.addAttribute("userPhone", incUser.getPhone());
            }
            model.mergeAttributes(errors);
            return "userEdit";
        }
        if (incUser == null) {
            redirectAttributes.addFlashAttribute("errorMessage", ERROR_USER_MESSAGE);
        } else {
            userService.saveUser(userForm, incUser, form);
            redirectAttributes.addFlashAttribute("successMessage", SUCCESS_SAVING_USER_MESSAGE);
        }
        return "redirect:/userlist";
    }

    @PostMapping(value = "/edit", params = {"delete"})
    public String userDelete(@RequestParam("userPhone") String userPhone, RedirectAttributes redirectAttributes) {
        User user = userService.findByPhone(userPhone);
        if (user == null) {
            redirectAttributes.addFlashAttribute("errorMessage", ERROR_USER_MESSAGE);
            return "redirect:/userlist";
        } else {
            redirectAttributes.addFlashAttribute("successMessage", SUCCESS_DELETE_USER_MESSAGE);
            userService.deleteUser(user.getPhone());
        }
        System.out.println("user deleted");
        return "redirect:/userlist";
    }

    @GetMapping("/profile")
    public String userProfileForm(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@Valid User userForm,
                                @RequestParam("userPhone") String incUserPhone,
                                BindingResult bindingResult, Model model) {
        User incUser = userService.findByPhone(incUserPhone);
        profileValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getFieldErrors(bindingResult);
            model.addAttribute("user", incUser);
            model.mergeAttributes(errors);
            return "profile";
        }
        User updatedUser = userService.updateProfile(userForm, incUser);
        model.addAttribute("user", updatedUser);
        model.addAttribute("successMessage", SUCCESS_SAVING_USER_MESSAGE);
        System.out.println("User updated successful");

        return "profile";
    }

}
