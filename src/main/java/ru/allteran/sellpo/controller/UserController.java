package ru.allteran.sellpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.allteran.sellpo.domain.Role;
import ru.allteran.sellpo.domain.User;
import ru.allteran.sellpo.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/userList")
    public String userList(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);

        return "userList";
    }

    @GetMapping("/user/{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        return "userEdit";
    }

    @PostMapping(value = "/user/{user}", params = {"save"})
    public String userSave(
            @RequestParam("phone") User user,
            String firstName, String lastName, String phone, String password, Map<String, String> form) {
        //TODO: use userValidator to validate all fields
        userService.userSave(user, form, phone, firstName, lastName, password);
        System.out.println("user saved");
        return "redirect:/user/" + user.getPhone();
    }

    @PostMapping(value = "/user/{user}", params = {"delete"})
    public String userDelete(User user) {
        System.out.println("user deleted");
        return "redirect:/userList";
    }

}
