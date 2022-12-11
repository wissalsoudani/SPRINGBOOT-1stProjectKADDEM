package tn.esprit.Spring.Services;

import tn.esprit.Spring.Entities.Specialite;
import tn.esprit.Spring.Entities.Universite;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IUnivresiteService {
    public Universite addUniversite(Universite c);
    public Universite updateUniversite(Universite c);
    public Universite getById(Integer IdUniversite);
    public List<Universite> getAllUniversite();
    public void removeUniversite(Integer IdUniversite);
    Map<Specialite,Float> getMontantContartEntreDeuxDate(int idUniv, LocalDate startDate, LocalDate endDate);

}
