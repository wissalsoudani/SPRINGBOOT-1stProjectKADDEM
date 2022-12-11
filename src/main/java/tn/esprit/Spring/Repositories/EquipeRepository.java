package tn.esprit.Spring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.Spring.Entities.Equipe;
import tn.esprit.Spring.Entities.Etudiant;

import java.util.List;

public interface EquipeRepository extends JpaRepository<Equipe,Integer> {

}
