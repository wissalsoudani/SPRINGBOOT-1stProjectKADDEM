package tn.esprit.Spring.Services;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import tn.esprit.Spring.Entities.Contrat;
import tn.esprit.Spring.Entities.Equipe;
import tn.esprit.Spring.Entities.Etudiant;
import tn.esprit.Spring.Repositories.ContratRepository;
import tn.esprit.Spring.Repositories.EtudiantRepository;
import tn.esprit.Spring.Util.HelperClass;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;
import java.util.List;
@Slf4j
@Service
public class IContratServiceImp implements IContratService{

    @Autowired
    ContratRepository contratRepository;
    @Autowired
    EtudiantRepository etudiantRepository;


    @Override
    public Contrat addContrat(Contrat c) {
        return contratRepository.save(c);
    }

    @Override
    public Contrat updateContrat(Contrat c) {
        return contratRepository.save(c);
    }

    @Override
    public Contrat getById(Integer IdContrat) {
        return contratRepository.findById(IdContrat).orElse(null);
    }

    @Override
    public List<Contrat> getAllContrat() {
        return contratRepository.findAll();
    }

    @Override
    public void removeContrat(Integer IdContrat) {
        contratRepository.deleteById(IdContrat);
    }

    @Override
    public Contrat affectContratToEtudiant(Contrat ce, String nomE, String prenomE) {
     /*   Etudiant etudiant=etudiantRepository.findByNomEAndPrenomE(nomE, prenomE);
        Contrat contrat=contratRepository.findById(ce.getIdContrat()).orElse(null);

     int ceEtud=0 ;
     List<Contrat> contrats=etudiant.getContrats();
     for(Contrat c:contrats) {
         if (!c.getArchive()) {
             ceEtud++;
         }
     }
     if(etudiant!=null &&ceEtud<=5){
         ce.setEtudiant(etudiant);
     }
     return contratRepository.save(ce);*/

        List <Etudiant> etudiants = etudiantRepository.findAll();
        Etudiant etudiant=null;
        for (Etudiant etudiant1:etudiants)
            if(etudiant1.getNomE().equals(nomE)&&etudiant1.getPrenomE().equals(prenomE)){
                etudiant=etudiant1;
            }
        int nbrContratActifs=0;
        if(etudiant!=null){
            for (Contrat c : etudiant.getContrats()){
                if (c.getArchive().equals(false)) {
                    nbrContratActifs++;
                }
            }
            if(nbrContratActifs<5){
                ce.setEtudiant(etudiant);
            }
        } return contratRepository.save(ce);
    }

    @Override
    public Integer nbContratsValides(Date startDate, Date endDate) {
        List<Contrat> contrats=contratRepository.findAll();
        int i=0;
        for (Contrat ct:contrats) {
            if (ct.getDateDebutContrat().toString().equals(startDate.toString())
                    &&
                    ct.getDateFinContrat().toString().equals(endDate.toString()))
            {
                i++;
            }
        }
        return i;
    }

    //this function is missing something f TP à voir avec l'administration par le prof
    // travail du grp pas correct
    public float getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate) {
        List<Contrat> contrats = contratRepository.findAll();
        // SimpleDateFormat df=new SimpleDateFormat("DD/MM/YYYY");
        //  String SD= startDate.toString();
    // String ED= endDate.toString();
    // Date dateA=df.parse(String SD);
    // Date dateB=df.parse(String ED);
    long diff = endDate.getTime() - startDate.getTime();
    double chiffreAffaire = 0;
    //double resultat =0 ;
        for (Contrat ct : contrats) {
        if (ct.getArchive() == false) {
            if ((ct.getDateDebutContrat().before(startDate)) && (ct.getDateFinContrat().after(endDate))) {

                double diffMois = diff / (1000 * 60 * 60 * 24 * 30);
                double resultat =0 ;
                switch (ct.getSpecialite()) {
                    case IA:
                        chiffreAffaire =+ diffMois * 300;
                        break;
                    case RESEAUX:
                        chiffreAffaire =+ diffMois * 350;
                        break;
                    case CLOUD:
                        chiffreAffaire =+ diffMois * 400;
                        break;
                    case SECURITE:
                        chiffreAffaire =+ diffMois * 450;
                        break;
                }
                return (float) chiffreAffaire;
            }
        }
    }
        return (float) chiffreAffaire;
}

@Transactional
public void archiveContracts(){
        contratRepository.findByArchiveIsFalseAndDateFinContrat(new Date()).stream()
                .forEach(contrat -> contrat.setArchive(true));
    }

    @Scheduled(fixedRate = 60000)
    @Override
    public void retrieveAndUpdateStatusContrat() {
    //List <Contrat> contrats=contratRepository.findByArchiveIsFalse();
    /*this.archiveContracts();
    contrats.stream()
            .filter(contrat ->
                    HelperClass.computeDifferenceBetweenDateInDays(new Date(),contrat.getDateFinContrat())<16)
            .forEach(contrat ->
                    log.info("le contrat de l'etudiant:" +contrat.getEtudiant().getNomE()));*/


            etudiantRepository.findAll().stream()
                    .map(Etudiant::getEquipes)
                    .flatMap(Collection::stream)
                    .forEach(equipe -> log.info(equipe.getNomEquipe()));

      }



    }




