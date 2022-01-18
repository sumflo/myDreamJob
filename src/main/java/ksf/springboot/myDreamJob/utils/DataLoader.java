package ksf.springboot.myDreamJob.utils;

import ksf.springboot.myDreamJob.model.ClientApp;
import ksf.springboot.myDreamJob.model.JobAdvertisement;
import ksf.springboot.myDreamJob.repositories.ClientAppRepository;
import ksf.springboot.myDreamJob.repositories.JobAdvertisementRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class DataLoader implements CommandLineRunner { // or ApplicationRunner

    private final ClientAppRepository clientAppRepository;
    private final JobAdvertisementRepository jobAdvertisementRepository;

    public DataLoader(ClientAppRepository clientAppRepository, JobAdvertisementRepository jobAdvertisementRepository) {
        this.clientAppRepository = clientAppRepository;
        this.jobAdvertisementRepository = jobAdvertisementRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<JobAdvertisement> phoenixPositions = new ArrayList<>();
        ClientApp phoenixComputerService = new ClientApp(UUID.randomUUID(), "Phoenix Computer Service",
                "phoenixComputerService@gmail.com", phoenixPositions);
        JobAdvertisement computerRepairer = new JobAdvertisement(phoenixComputerService, "Computer mechanic",
                "Budapest", "We are looking for a mechanic who can lift any machine from its ashes.");
        JobAdvertisement maid = new JobAdvertisement(phoenixComputerService, "Maid",
                "Budapest", "We are looking for a person who can clean the ashes without a trace if necessary.");
        JobAdvertisement masseur = new JobAdvertisement(phoenixComputerService, "Masseur",
                "Budapest", "We are looking for a professional for the maintenance of our tired professionals hands.");
        phoenixComputerService.getJobAdvertisements().add(computerRepairer);
        phoenixComputerService.getJobAdvertisements().add(maid);
        phoenixComputerService.getJobAdvertisements().add(masseur);

        clientAppRepository.save(phoenixComputerService);
        jobAdvertisementRepository.save(computerRepairer);
        jobAdvertisementRepository.save(maid);
        jobAdvertisementRepository.save(masseur);

        List<JobAdvertisement> dreamPositions = new ArrayList<>();
        ClientApp dreamDesignWorks = new ClientApp(UUID.randomUUID(), "Dream Design Works",
                "dreamDesignWorks@gmail.com", dreamPositions);
        JobAdvertisement designer = new JobAdvertisement(dreamDesignWorks, "Designer",
                "Vienna", "We are looking for the best designer ever.");
        dreamDesignWorks.getJobAdvertisements().add(designer);

        clientAppRepository.save(dreamDesignWorks);
        jobAdvertisementRepository.save(designer);

    }

}
