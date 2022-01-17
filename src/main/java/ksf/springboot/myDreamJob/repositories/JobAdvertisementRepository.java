package ksf.springboot.myDreamJob.repositories;

import ksf.springboot.myDreamJob.model.JobAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobAdvertisementRepository extends JpaRepository<JobAdvertisement, UUID> { // or CrudRepository
}
