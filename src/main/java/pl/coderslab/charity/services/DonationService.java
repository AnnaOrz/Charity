package pl.coderslab.charity.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.models.Donation;
import pl.coderslab.charity.models.Institution;
import pl.coderslab.charity.repositories.DonationRepository;

import java.util.List;

@Service
@Transactional
public class DonationService {
    private final DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }


    public void create(Donation donation) {
        donationRepository.save(donation);
    }

    public Donation read(Long id) {
        return donationRepository.findById(id).orElse(null);
    }

    public List<Donation> readAll() {
        return donationRepository.findAll();
    }

    public boolean update(Donation donation) {
        if (donationRepository.existsById(donation.getId())) {
            donationRepository.save(donation);
            return true;
        } else return false;
    }

    public boolean delete(Long id) {
        if (read(id) == null) {
            return false;
        } else {
            donationRepository.delete(read(id));
            return true;
        }
    }

}
