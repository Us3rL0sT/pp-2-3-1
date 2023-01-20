package allDirectories.controllers;

import allDirectories.models.User;
import allDirectories.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String showAllUsers(Model model) {

        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUs", allUsers);

        return "all-users";
    }


    @RequestMapping("/addNewUser")
    public String addNewUser(Model model) {

        User user = new User();
        model.addAttribute("user", user);

        return "user-info";
    }

    @PostMapping("/user-info")
    public String saveUser(@ModelAttribute("user") User user) {

        userService.add(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.get(id));
        return "edit";
    }

    @RequestMapping("/{id}")
    public String patchEdit(@ModelAttribute("user") User user,
                            @PathVariable("id") int id) {
        userService.update(id, user);
        return "redirect:/";
    }

    @RequestMapping("/{id}/delete")
    public String deleteUser(@PathVariable(name = "id") int id) {
        userService.delete(id);
        return "redirect:/";
    }
}


