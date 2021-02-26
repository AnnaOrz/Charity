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
import pl.coderslab.charity.repositories.CategoryRepository;
import pl.coderslab.charity.repositories.DonationRepository;
import pl.coderslab.charity.repositories.InstitutionRepository;
import pl.coderslab.charity.services.UserService;

import javax.validation.Valid;
import java.util.List;


@Controller
    @RequestMapping("/donation")
    public class DonationController{
        private final InstitutionRepository institutionRepository;
        private final DonationRepository donationRepository;
        private final CategoryRepository categoryRepository;
        private final UserService userService;

        public DonationController(InstitutionRepository institutionRepository, DonationRepository donationRepository, CategoryRepository categoryRepository, UserService userService) {
            this.institutionRepository = institutionRepository;
            this.donationRepository = donationRepository;
            this.categoryRepository = categoryRepository;
            this.userService = userService;
        }


    @GetMapping("")
    public String goToDonationForm(Model model){
            model.addAttribute("donation", new Donation());
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("institutions", institutionRepository.findAll());
        return "form-donation";
    }

    @PostMapping("")
    public String addDonation(@ModelAttribute @Valid Donation donation, BindingResult result){
            if(result.hasErrors()){ return "form-dontation"; }
            User user = userService.getCurrentUser();
        donationRepository.save(donation);
        if(user != null) {
            List<Donation> userDonations = user.getDonations();
            userDonations.add(donation);
            user.setDonations(userDonations);
            userService.update(user);
        }
            return "form-confirmation";
    }
}
