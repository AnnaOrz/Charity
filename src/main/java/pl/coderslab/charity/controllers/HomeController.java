package pl.coderslab.charity.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.models.Donation;
import pl.coderslab.charity.models.Role;
import pl.coderslab.charity.models.User;
import pl.coderslab.charity.services.DonationService;
import pl.coderslab.charity.services.InstitutionService;
import pl.coderslab.charity.services.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;


@Controller
public class HomeController {
    private  final InstitutionService institutionService;
    private final UserService userService;
    private final DonationService donationService;

    public HomeController(InstitutionService institutionService, UserService userService, DonationService donationService) {
        this.institutionService = institutionService;
        this.userService = userService;
        this.donationService = donationService;
    }


    @RequestMapping("")
    public String homeAction(Model model){
        model.addAttribute("institutions", institutionService.readAll().size());
        model.addAttribute("donations", donationService.readAll().size());
        List<Donation> allDonations = donationService.readAll();
        Long numberOfBags = allDonations.stream().mapToLong(Donation::getQuantity).sum();
        model.addAttribute("bags", numberOfBags);
        return "landing-page";

    }
    @GetMapping("/register")
    public String registerGoToForm(Model model){
        model.addAttribute("user" , new User());
        return "form-registration";
    }
    @PostMapping("/register")
    public String registerUser(@ModelAttribute  @Valid User user, BindingResult result){
        if(userService.emailExist(user.getEmail())){result.addError(new ObjectError("email", "username exist")); }
        if(result.hasErrors() ){ return "form-registration"; }
            user.setEnabled(true);
            user.setTokenExpired(false);
            userService.create(user);
            return "redirect:";
        }

    @RequestMapping("/logged")
    public String loggedInChooseDirection(){
            Set<Role> roles = userService.getCurrentUser().getRoles();
            if (roles.stream().noneMatch(r -> r.getName().equals("ROLE_ADMIN"))) {
                return "redirect:/user";
            } else {
                return "redirect:/admin";
            }
    }
    @GetMapping("/login")
    public String goToLogForm(Model model){
        model.addAttribute("user", new User());
        return "form-login";
    }
}
