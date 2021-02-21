package pl.coderslab.charity.controllers;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.models.User;
import pl.coderslab.charity.repositories.UserRepository;
import pl.coderslab.charity.services.UserService;

import java.util.List;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {
    private final UserRepository userRepository;
    private final UserService userService;

    public AdminController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }
    @RequestMapping("")
    public String adminMenu(){
        return "/admin/adminMenu";
    }

    @RequestMapping("/users")
    public String showUsers(Model model){
        List<User> userList = userRepository.findAll(Sort.by("lastName"));
        model.addAttribute("users", userList);
        return "/admin/users";
    }
    @RequestMapping("/deleteUser/{id:[0-9]+}")
    public String deleteUser(@PathVariable Long id, Model model){
        userService.delete(id);
        model.addAttribute("info", "Usunięto użytkownika");
        return "redirect:/admin/users";
    }
    @RequestMapping("/disable/{id:[0-9]+}")
    public String blockUser(@PathVariable Long id, Model model) {
        User user = userService.read(id);
        if (user != null) {
            userService.disable(id);
        }
        return "redirect:/admin/users";
    }

}
