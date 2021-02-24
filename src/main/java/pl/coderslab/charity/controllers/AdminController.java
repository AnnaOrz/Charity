package pl.coderslab.charity.controllers;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.models.Institution;
import pl.coderslab.charity.models.Role;
import pl.coderslab.charity.models.User;
import pl.coderslab.charity.repositories.RoleRepository;
import pl.coderslab.charity.repositories.UserRepository;
import pl.coderslab.charity.services.InstitutionService;
import pl.coderslab.charity.services.UserService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final InstitutionService institutionService;
    private final RoleRepository roleRepository;

    public AdminController(UserRepository userRepository, UserService userService, InstitutionService institutionService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.institutionService = institutionService;
        this.roleRepository = roleRepository;
    }

    @RequestMapping("")
    public String adminMenu() {
        return "admin/admin-main";
    }

    @RequestMapping("/users")
    public String showUsers(Model model) {
        List<User> userList = userRepository.findAll(Sort.by("lastName"));
        model.addAttribute("users", userList);
        return "/admin/users";
    }

    @RequestMapping("/deleteUser/{id:[0-9]+}")
    public String deleteUser(@PathVariable Long id, Model model) {
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

    @GetMapping("/editUser/{id:[0-9]+}")
    public String editUserForm(@PathVariable Long id, Model model) {
        User user = userService.read(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "admin/form-user-edit";
        } else return "redirect:/users";
    }

    @PostMapping("/editUser/{id:[0-9]+}")
    public String editUser(@ModelAttribute @Valid User user, BindingResult bindingResult, @PathVariable Long id, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/form-user-edit";
        }
        User userToUpdate = userService.read(id);
        if (userToUpdate == null) {
            model.addAttribute("message", "nie znaleziono takiego użytkowika");
            return "admin/form-user-edit";
        }
        if (user.getPassword().equals(userToUpdate.getPassword())) {
            user.setPassword(userService.encodeUserPassword(user.getPassword()));
        }
        user.setDonations(userToUpdate.getDonations());
        user.setRoles(userToUpdate.getRoles());
        userService.update(user);
        return "redirect:/admin/users";
    }

    @RequestMapping("/institutions")
    public String goToInstitutions(Model model) {
        model.addAttribute("institutions", institutionService.readAll());
        return "admin/institutions";
    }
    @GetMapping("/addInstitution")
    public String createInstitution(Model model){
        model.addAttribute("institution", new Institution());
        return "admin/form-institution-edit-create";
    }
    @PostMapping("/addInstitution")
        public String addInstitution(@ModelAttribute @Valid Institution institution, BindingResult result){
        if(result.hasErrors()){ return "admin/form-institution-edit-create"; }
        institutionService.create(institution);
        return "redirect:institutions";
        }

    @GetMapping("/editInstitution/{id:[0-9]+}")
    public String goToInstitutionForm(Model model, @PathVariable Long id) {
        if (institutionService.read(id) != null) {
            model.addAttribute("institution", institutionService.read(id));
            return "admin/form-institution-edit-create";
        } else return "admin/institutions";
    }

    @PostMapping("/editInstitution/{id:[0-9]+}")
    public String updateInstitution(@ModelAttribute @Valid Institution institution, BindingResult result){
        if(result.hasErrors()){ return "admin/form-institution-edit-create"; }
        institutionService.update(institution);
        return "redirect:/admin/institutions";
    }

    @RequestMapping("/deleteInstitution/{id:[0-9]+}")
    public String deleteInstitution(@PathVariable Long id){
        institutionService.delete(id);
        return "redirect:/admin/institutions";
    }
    @RequestMapping("/admins")
    public String goToAdmins(Model model) {
        Role admin = roleRepository.findByName("ROLE_ADMIN");
        model.addAttribute("admins", userService.readByRole(admin));
        return "admin/admins";
    }
    @GetMapping("/createAdmin")
    public String goToAdminForm(Model model) {
        model.addAttribute("admin", new User());
        return "admin/form-admin-edit";
    }
    @PostMapping("/createAdmin")
    public String createAdmin(@ModelAttribute @Valid User admin, BindingResult result){
        if(result.hasErrors()){return "admin/form-admin-edit";}
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_ADMIN"));
        admin.setRoles(roles);
        userService.create(admin);
        return "redirect:admin/admins";
    }
    @RequestMapping("/deleteAdmin/{id:[0-9]+}")
    public String deleteAdmin(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/admin/admins";
    }
}
