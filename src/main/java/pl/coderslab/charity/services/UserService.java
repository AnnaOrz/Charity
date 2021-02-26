package pl.coderslab.charity.services;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.models.Role;
import pl.coderslab.charity.models.User;
import pl.coderslab.charity.repositories.RoleRepository;
import pl.coderslab.charity.repositories.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean emailExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public void create(User user) {
        if (!emailExist(user.getEmail())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setEnabled(true);
            user.setTokenExpired(false);
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName("ROLE_USER"));
            user.setRoles(roles);
            userRepository.save(user);
        }

    }

    public void createAdmin(User user) {
        create(user);
        Set<Role> roles = user.getRoles();
        roles.add(roleRepository.findByName("ROLE_ADMIN"));
        user.setRoles(roles);
        userRepository.save(user);
    }

    public User read(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    public void update(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (optionalUser.isPresent()) {
            if (!optionalUser.get().getPassword().equals(user.getPassword())) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                //if password changed encode new password, if password is the same , skip encoding
            }
            userRepository.save(user);
        }
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public void disable(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            userRepository.disableUser(userId);
        }
    }

    public List<User> readByRole(Role role) {
        return userRepository.findAllByRolesContains(role);
    }

/*    public void changeEnabled(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.enableUser(id);
        }
    }*/

    public String encodeUserPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public User getCurrentUser() {
        UserDetails current = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userRepository.findByEmail(current.getUsername()).isPresent()) {
            return userRepository.findByEmail(current.getUsername()).get();
        } else {
            return null;
        }
    }

}
