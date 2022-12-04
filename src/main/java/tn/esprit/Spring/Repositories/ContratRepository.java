package tn.esprit.Spring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.Spring.Entities.Contrat;

import java.util.Date;
import java.util.List;

public interface ContratRepository extends JpaRepository<Contrat,Integer> {

     int countByArchiveFalseAndEtudiantNomEAndEtudiantPrenomE(String NomE, String PrenomE);
     List<Contrat> findByArchiveIsFalse();
     List<Contrat> findByArchiveIsFalseAndDateFinContrat(Date date);


}
