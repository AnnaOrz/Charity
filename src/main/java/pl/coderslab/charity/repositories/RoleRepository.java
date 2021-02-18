package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.models.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String role_user);
}