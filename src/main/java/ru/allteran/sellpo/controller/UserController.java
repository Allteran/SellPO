package ru.allteran.sellpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    @Autowired
    private UserService userService;
    @Autowired
    private UserEditValidator userEditValidator;
    @Autowired
    private RoleRepository rolesRepo;


    @GetMapping("/userlist")
    public String userList(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);

        return "userList";
    }

    @GetMapping("/user/{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        Set<Role> roles = new HashSet<>(rolesRepo.findAll());
        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        return "userEdit";
    }

    @PostMapping(value = "/user", params = {"save"})
    public String userSave(
            @Valid User userForm, @RequestParam("userPhone") User incUser, BindingResult bindingResult, Model model) {
        //TODO: use userValidator to validate all fields
        userEditValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getFieldErrors(bindingResult);
            Set<Role> roles = new HashSet<>(rolesRepo.findAll());
            model.addAttribute("roles", roles);
            model.addAttribute("userPhone", incUser.getPhone());
            model.mergeAttributes(errors);
            return "userEdit";
        }

        userService.userSave(userForm, incUser);
        System.out.println("user saved");
        return "redirect:/userlist";
    }

//    @PostMapping(value = "/user/{user}", params = {"delete"})
//    public String userDelete(User user) {
//        System.out.println("user deleted");
//        return "redirect:/userList";
//    }

}
