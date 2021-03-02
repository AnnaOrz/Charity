package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.models.Donation;
import pl.coderslab.charity.models.User;
import pl.coderslab.charity.services.DonationService;
import pl.coderslab.charity.services.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final DonationService donationService;

    public UserController(UserService userService, DonationService donationService) {
        this.userService = userService;
        this.donationService = donationService;
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
        User oldUser = userService.read(user.getId());
        user.setDonations(oldUser.getDonations());
        user.setRoles(oldUser.getRoles());
        user.setTokenExpired(oldUser.isTokenExpired());
        user.setEnabled(oldUser.isEnabled());
        userService.update(user);
        return "redirect:/user";
    }
    @GetMapping ("/donationDetails/{id:[0-9]+}")
    public String goToDonationDetail(@PathVariable Long id, Model model){
        model.addAttribute("donation", donationService.read(id));
        return "donation-details";

    }
    @PostMapping("/donationDetails/{id:[0-9]+}")
    public String confirmCollection(@PathVariable Long id){
        donationService.confirmCollection(id);
        return "redirect:/user/donationDetails/"+id;


    }
}



