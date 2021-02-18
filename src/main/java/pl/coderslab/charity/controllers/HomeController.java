package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.models.Donation;
import pl.coderslab.charity.models.Institution;
import pl.coderslab.charity.models.User;
import pl.coderslab.charity.repositories.DonationRepository;
import pl.coderslab.charity.repositories.InstitutionRepository;
import pl.coderslab.charity.repositories.UserRepository;

import javax.validation.Valid;
import java.util.List;


@Controller
public class HomeController {
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final UserRepository userRepository;

    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository, UserRepository userRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.userRepository = userRepository;
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
        }
        userRepository.save(user); //co z ochroną przed powieleniem maila? czy unique przy mailu wystarczy?
        return "redirect: ";
    }
}
