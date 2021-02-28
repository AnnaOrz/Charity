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
import pl.coderslab.charity.services.DonationService;
import pl.coderslab.charity.services.InstitutionService;
import pl.coderslab.charity.services.UserService;

import javax.validation.Valid;
import java.util.List;


@Controller
    @RequestMapping("/donation")
    public class DonationController{
        private final InstitutionService institutionService;
        private final CategoryRepository categoryRepository;
        private final UserService userService;
        private final DonationService donationService;

        public DonationController(InstitutionService institutionService, CategoryRepository categoryRepository, UserService userService, DonationService donationService) {
            this.institutionService = institutionService;
            this.categoryRepository = categoryRepository;
            this.userService = userService;
            this.donationService = donationService;
        }


    @GetMapping("")
    public String goToDonationForm(Model model){
            model.addAttribute("donation", new Donation());
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("institutions", institutionService.readAll());
        return "form-donation";
    }

    @PostMapping("")
    public String addDonation(@ModelAttribute @Valid Donation donation, BindingResult result){
            if(result.hasErrors()){ return "form-dontation"; }
            User user = userService.getCurrentUser();
        donationService.create(donation);
        if(user != null) {
            List<Donation> userDonations = user.getDonations();
            userDonations.add(donation);
            user.setDonations(userDonations);
            userService.update(user);
        }
            return "form-confirmation";
    }
}
