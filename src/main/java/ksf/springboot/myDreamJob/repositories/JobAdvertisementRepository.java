package ksf.springboot.myDreamJob.repositories;

import ksf.springboot.myDreamJob.model.ClientApp;
import ksf.springboot.myDreamJob.model.JobAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JobAdvertisementRepository extends JpaRepository<JobAdvertisement, UUID> { // or CrudRepository

    List<JobAdvertisement> findAll();

    Optional<JobAdvertisement> findById(UUID id);

}
