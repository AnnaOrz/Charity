package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.models.Donation;
import pl.coderslab.charity.models.User;
import pl.coderslab.charity.services.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("")
    public String goToUserPagePage(Model model) {
        List<Donation> donations = userService.getUserDonationsSorted(userService.getCurrentUser());
        model.addAttribute("donations", donations);
        return "user-main";
    }

    @GetMapping("/edit")
    public String goToEditForm(Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        return "form-user-edit";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "form-user-edit";
        }
        userService.update(user);
        return "redirect:";
    }
}



