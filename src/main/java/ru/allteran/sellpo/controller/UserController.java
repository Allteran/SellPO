package ru.allteran.sellpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.allteran.sellpo.domain.Role;
import ru.allteran.sellpo.domain.User;
import ru.allteran.sellpo.repo.RoleRepository;
import ru.allteran.sellpo.service.UserEditValidator;
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
    private UserEditValidator userEditValidator;
    @Autowired
    private RoleRepository rolesRepo;

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/userlist")
    public String userList(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);

        return "userList";
    }

    //TODO: @PReAuthorize not working with real ADMIN user
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user/{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        Set<Role> roles = new HashSet<>(rolesRepo.findAll());
        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        model.addAttribute("userPhone", user.getPhone());
        return "userEdit";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/user", params = {"save"})
    public String userSave(
            @Valid User userForm, @RequestParam("userPhone") User incUser,
            @RequestParam Map<String, String> form, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        //TODO: use userValidator to validate all fields
        userEditValidator.validate(userForm, bindingResult);
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
            userService.userSave(userForm, incUser, form);
            redirectAttributes.addFlashAttribute("successMessage", SUCCESS_SAVING_USER_MESSAGE);
        }
        return "redirect:/userlist";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/user", params = {"delete"})
    public String userDelete(@RequestParam("userPhone") User user, RedirectAttributes redirectAttributes) {
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

}
