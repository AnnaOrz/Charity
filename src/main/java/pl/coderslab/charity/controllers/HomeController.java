package pl.coderslab.charity.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.models.Donation;
import pl.coderslab.charity.models.Institution;
import pl.coderslab.charity.models.Role;
import pl.coderslab.charity.models.User;
import pl.coderslab.charity.repositories.DonationRepository;
import pl.coderslab.charity.repositories.InstitutionRepository;
import pl.coderslab.charity.repositories.UserRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;


@Controller
public class HomeController {
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public User getCurrentUser() {
        UserDetails current = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userRepository.findByEmail(current.getUsername()) != null) {
            return userRepository.findByEmail(current.getUsername());
        } else {
            return null;
        }
    }


    @RequestMapping("")
    public String homeAction(Model model){
        List<Institution> institutions = institutionRepository.findAll();
        model.addAttribute("institutions", institutions);
        model.addAttribute("donations", donationRepository.countAllDonations());

        List<Donation> allDonations = donationRepository.findAll();
        Long numberOfBags = allDonations.stream().mapToLong(Donation::getQuantity).sum();
        model.addAttribute("bags", numberOfBags);
        return "landingPage";

    }
    @GetMapping("/register")
    public String registerGoToForm(Model model){
        model.addAttribute("user" , new User());
        return "registrationForm";
    }
    @PostMapping("/register")
    public String registerUser(@ModelAttribute  @Valid User user, BindingResult result){
        if(result.hasErrors()){
            return "registrationForm";
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setPasswordConfirm(passwordEncoder.encode(user.getPasswordConfirm()));
            user.setEnabled(true);
            user.setTokenExpired(false);
            userRepository.save(user); //co z ochroną przed powieleniem maila? czy unique przy mailu wystarczy?
            return "redirect:"; //przerzucić to raczej do UserService!
        }
    }
    @RequestMapping("/logged")
    public String loggedInChooseDirection(){
            Set<Role> roles = getCurrentUser().getRoles();
            if (roles.stream().noneMatch(r -> r.getName().equals("ROLE_ADMIN"))) {
                return "redirect:/user";
            } else {
                return "redirect:/admin";
            }
    }
    @RequestMapping("/user")
    public String goToUserPagePage() {
            return "userPage";
        }
    @RequestMapping("/admin")
    public String goToAdminPage() {
        return "adminPage";
    }

}
