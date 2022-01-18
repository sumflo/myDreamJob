package ksf.springboot.myDreamJob.services;

import ksf.springboot.myDreamJob.model.ClientApp;
import ksf.springboot.myDreamJob.model.JobAdvertisement;
import ksf.springboot.myDreamJob.repositories.JobAdvertisementRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JobAdvertisementService {

    private final JobAdvertisementRepository jobAdvertisementRepository;

    public JobAdvertisementService(JobAdvertisementRepository jobAdvertisementRepository) {
        this.jobAdvertisementRepository = jobAdvertisementRepository;
    }

    public UUID createAdvertisement(ClientApp clientApp, String positionName, String placeOfWork, String description){
        JobAdvertisement currentAdvertisement = new JobAdvertisement(clientApp, positionName, placeOfWork, description);
        jobAdvertisementRepository.save(currentAdvertisement);
        return currentAdvertisement.getId();
    }

/*    public List<JobAdvertisement> search(String keyWord, String location){

        List<JobAdvertisement> allAdvertisement = jobAdvertisementRepository.findAll();
        List<JobAdvertisement> jobAdvertisementsMatches = new ArrayList<>();

        for (int i = 0; i < allAdvertisement.size(); i++) {
            if(allAdvertisement.get(i).getPositionName().toLowerCase().contains(keyWord.toLowerCase()) &&
                    allAdvertisement.get(i).getPlaceOfWork().toLowerCase().contains(location.toLowerCase())){
                jobAdvertisementsMatches.add(allAdvertisement.get(i));
            }
        }
        return jobAdvertisementsMatches;
    }*/

    public Optional<JobAdvertisement> getAdvertisementById(UUID id){
        return jobAdvertisementRepository.findById(id);
    }

    public List<UUID> searchForAdvertisement(String keyWord, String location){

        List<JobAdvertisement> allAdvertisement = jobAdvertisementRepository.findAll();
        List<UUID> jobAdvertisementUUIDsMatches = new ArrayList<>();

        //ToDo: A szerver első lépésben ellenőrzi az api kulcs érvényességét. Nem érvényes api kulcs esetén hibaüzenettel tér vissza.

        for (int i = 0; i < allAdvertisement.size(); i++) {
            if(allAdvertisement.get(i).getPositionName().toLowerCase().contains(keyWord.toLowerCase()) &&
                    allAdvertisement.get(i).getPlaceOfWork().toLowerCase().contains(location.toLowerCase())){
                jobAdvertisementUUIDsMatches.add(allAdvertisement.get(i).getId());
            }
        }

        //ToDo: Ha a keresés sikerrel járt a kliens számára egy URL listával kell visszatérni a hírdetésekhez tartozó URL-el.

        return jobAdvertisementUUIDsMatches;
    }

    public List<JobAdvertisement> getAllAdvertisement(){
        return jobAdvertisementRepository.findAll();
    }

}
