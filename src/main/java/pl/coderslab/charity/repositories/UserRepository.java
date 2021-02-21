package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.models.Donation;
import pl.coderslab.charity.models.Role;
import pl.coderslab.charity.models.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.email=?1, u.roles=?2, u.password=?3, " +
            " u.firstName=?4, u.lastName=?5, u.donations=?6,u.enabled=?7, u.tokenExpired=?8 WHERE u.id =?9")
    void updateUser(String email, Set<Role> roles, String password, String firstName, String lastName,
                    List<Donation> donations,boolean enabled, boolean tokenExpired,Long id );

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.enabled=false WHERE u.id=?1")
    void disableUser(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.enabled=true WHERE u.id=?1")
    void enableUser(Long id);
}
