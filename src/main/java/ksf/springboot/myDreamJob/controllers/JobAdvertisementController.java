package ksf.springboot.myDreamJob.controllers;

import ksf.springboot.myDreamJob.model.ClientApp;
import ksf.springboot.myDreamJob.model.dto.JobAdvertisementDTO;
import ksf.springboot.myDreamJob.services.ClientAppService;
import ksf.springboot.myDreamJob.services.JobAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*") // preparation for frontend access
public class JobAdvertisementController {

    private JobAdvertisementService jobAdvertisementService;
    private ClientAppService clientAppService;

    @Autowired
    public JobAdvertisementController(JobAdvertisementService jobAdvertisementService,
                                      ClientAppService clientAppService) {
        this.jobAdvertisementService = jobAdvertisementService;
        this.clientAppService = clientAppService;
    }

    @PostMapping("/positions")
    public ResponseEntity<Void> createAdvertisement(@RequestBody JobAdvertisementDTO jobAdvertisementDTO){
        ClientApp currentClientApp = clientAppService.findById(jobAdvertisementDTO.getClientAppId()).orElseThrow();

        //ToDo: A szerver első lépésben ellenőrzi az api kulcs érvényességét.
        //ToDo: Nem érvényes api kulcs esetén hibaüzenettel tér vissza.

        jobAdvertisementService.createAdvertisement(currentClientApp, jobAdvertisementDTO.getPositionName(),
                jobAdvertisementDTO.getPlaceOfWork(), jobAdvertisementDTO.getDescription());
        return new ResponseEntity<>(HttpStatus.OK); //ToDo: térjen vissza egy URL-lel a responseban, hogy milyen oldalon érhető el a pozició
    }

    //@GetMapping("/search/{keyWord}")

}
