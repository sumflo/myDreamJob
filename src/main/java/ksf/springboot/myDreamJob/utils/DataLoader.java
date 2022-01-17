package ksf.springboot.myDreamJob.utils;

import ksf.springboot.myDreamJob.repositories.ClientAppRepository;
import ksf.springboot.myDreamJob.repositories.JobAdvertisementRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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

    }
}
