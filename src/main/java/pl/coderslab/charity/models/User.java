package pl.coderslab.charity.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Email
        @Column(unique = true)
        @NotBlank(message = "pole nie może być puste")
        private String email;

        /*
        @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER))
        private List<Role> roles */

        @NotBlank(message = "pole nie może być puste")
        private String password;

        @Transient
        private String passwordConfirm;

//każda dotacja ma jednego użytkownika, użytkownik może mieć wiele dotacji
        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private List<Donation> donations;

        private String firstName;
        private String lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
