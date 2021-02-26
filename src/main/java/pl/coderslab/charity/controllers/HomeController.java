package pl.coderslab.charity.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.models.Donation;
import pl.coderslab.charity.models.Institution;
import pl.coderslab.charity.models.Role;
import pl.coderslab.charity.models.User;
import pl.coderslab.charity.repositories.DonationRepository;
import pl.coderslab.charity.repositories.InstitutionRepository;
import pl.coderslab.charity.repositories.UserRepository;
import pl.coderslab.charity.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;


@Controller
public class HomeController {
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final RequestCache requestCache = new HttpSessionRequestCache();

    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository, UserRepository userRepository, UserService userService) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.userRepository = userRepository;
        this.userService = userService;

    }
    public User getCurrentUser() {
        UserDetails current = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userRepository.findByEmail(current.getUsername()).isPresent()) {
            return userRepository.findByEmail(current.getUsername()).get();
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
            Set<Role> roles = getCurrentUser().getRoles();
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
