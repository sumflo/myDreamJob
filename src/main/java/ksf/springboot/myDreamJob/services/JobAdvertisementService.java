package ksf.springboot.myDreamJob.services;

import ksf.springboot.myDreamJob.model.ClientApp;
import ksf.springboot.myDreamJob.model.JobAdvertisement;
import ksf.springboot.myDreamJob.repositories.JobAdvertisementRepository;
import org.springframework.stereotype.Service;

@Service
public class JobAdvertisementService {

    private final JobAdvertisementRepository jobAdvertisementRepository;

    public JobAdvertisementService(JobAdvertisementRepository jobAdvertisementRepository) {
        this.jobAdvertisementRepository = jobAdvertisementRepository;
    }

    public void createAdvertisement(ClientApp clientApp, String positionName, String placeOfWork, String description){
        jobAdvertisementRepository.save(new JobAdvertisement(clientApp, positionName, placeOfWork, description));
    }
}
