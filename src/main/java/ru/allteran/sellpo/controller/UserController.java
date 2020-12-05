package ru.allteran.sellpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
    @Autowired
    private UserService userService;
    @Autowired
    private UserEditValidator userEditValidator;
    @Autowired
    private RoleRepository rolesRepo;


    @GetMapping("/userList")
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

    @PostMapping(value = "/user/{user}", params = {"save"})
    public String userSave(
            @Valid User userForm,
            String firstName, String lastName, String phone, String password, Map<String, String> form,
            BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        //TODO: use userValidator to validate all fields
        if (StringUtils.isEmpty(phone)) {
            phone = userForm.getPhone();
        }
        userEditValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getFieldErrors(bindingResult);
            redirectAttributes.addFlashAttribute(errors);
            return "userEdit";
        }
        userService.userSave(userForm, form, phone, firstName, lastName, password);
        System.out.println("user saved");
        return "redirect:/user/" + userForm.getPhone();
    }

    @PostMapping(value = "/user/{user}", params = {"delete"})
    public String userDelete(User user) {
        System.out.println("user deleted");
        return "redirect:/userList";
    }

}
