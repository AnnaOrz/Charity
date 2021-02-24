package pl.coderslab.charity.services;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.models.Institution;
import pl.coderslab.charity.repositories.InstitutionRepository;

import java.util.List;

@Service
@Transactional
public class InstitutionService {
    private final InstitutionRepository institutionRepository;

    public InstitutionService(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }


    public void create(Institution institution) {
        institutionRepository.save(institution);
    }

    public Institution read(Long id) {
        return institutionRepository.findById(id).orElse(null);
    }

    public List<Institution> readAll() {
        return institutionRepository.findAll();
    }

    public boolean update(Institution institution) {
        if (institutionRepository.existsById(institution.getId())) {
            institutionRepository.save(institution);
            return true;
        } else return false;
    }

    public boolean delete(Long id) {
        if (read(id) == null) {
            return false;
        } else {
            institutionRepository.delete(read(id));
            return true;
        }
    }

}
