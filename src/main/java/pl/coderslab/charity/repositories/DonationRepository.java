package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.models.Donation;

import java.util.List;

public interface DonationRepository extends JpaRepository <Donation, Long> {
    @Query(value = "SELECT COUNT(d) FROM Donation d ")
    Long countAllDonations();






}
