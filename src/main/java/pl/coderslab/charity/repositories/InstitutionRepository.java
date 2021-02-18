package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.models.Institution;

import java.util.List;

public interface InstitutionRepository extends JpaRepository <Institution, Long> {
    List <Institution> findAll();
}
