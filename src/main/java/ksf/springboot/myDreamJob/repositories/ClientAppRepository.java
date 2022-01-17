package ksf.springboot.myDreamJob.repositories;

import ksf.springboot.myDreamJob.model.ClientApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientAppRepository extends JpaRepository<ClientApp, UUID> {

    List<ClientApp> findAll();

    Optional<ClientApp> findByEmail(String email);

    Optional<ClientApp> findById(UUID id);
}
