package ksf.springboot.myDreamJob.controllers;

import ksf.springboot.myDreamJob.model.ClientApp;
import ksf.springboot.myDreamJob.model.JobAdvertisement;
import ksf.springboot.myDreamJob.model.dto.JobAdvertisementDTO;
import ksf.springboot.myDreamJob.model.dto.SearchingRequestDTO;
import ksf.springboot.myDreamJob.services.ClientAppService;
import ksf.springboot.myDreamJob.services.JobAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*") // preparation for frontend access
public class JobAdvertisementController {

    private final JobAdvertisementService jobAdvertisementService;
    private final ClientAppService clientAppService;

    @Autowired
    public JobAdvertisementController(JobAdvertisementService jobAdvertisementService,
                                      ClientAppService clientAppService) {
        this.jobAdvertisementService = jobAdvertisementService;
        this.clientAppService = clientAppService;
    }

    @GetMapping("/positions/{id}")
    public Optional<JobAdvertisement> getAdvertisementById(@PathVariable("id")UUID id){
        return jobAdvertisementService.getAdvertisementById(id);
    }

    @PostMapping("/positions")
    public ResponseEntity<String> createAdvertisement(@RequestBody JobAdvertisementDTO jobAdvertisementDTO, @RequestHeader("X-My-API-Key-Token") UUID apiKey){

        String baseUrl = "http://localhost:8080/positions";
        ClientApp currentClientApp = clientAppService.findById(jobAdvertisementDTO.getClientAppId()).orElseThrow();

        if(clientAppService.findByApiKey(apiKey).isPresent() && currentClientApp.getApiKey().equals(apiKey)){ // -->> A szerver első lépésben ellenőrzi az api kulcs érvényességét.

            JobAdvertisement currentJobAdvertisement =
            jobAdvertisementService.getAdvertisementById(
                    jobAdvertisementService.createAdvertisement(currentClientApp, jobAdvertisementDTO.getPositionName(),
                            jobAdvertisementDTO.getPlaceOfWork(), jobAdvertisementDTO.getDescription())
            ).orElseThrow();

            String id = currentJobAdvertisement.getId().toString();

            String url = baseUrl + "/" + id;

            return new ResponseEntity<>(url, HttpStatus.OK); // -->> térjen vissza egy URL-lel a responseban, hogy milyen oldalon érhető el a pozició
        }else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN); //403 -->> Nem érvényes api kulcs esetén hibaüzenettel tér vissza.
        }

    }

    @GetMapping("/search")
    public ResponseEntity<List<String>> searchByPositionAndLocation(@RequestBody SearchingRequestDTO searchingRequestDTO, @RequestHeader("X-My-API-Key-Token") UUID apiKey){

        String baseUrl = "http://localhost:8080/positions";
        List<UUID> advertisementIdList = jobAdvertisementService.searchForAdvertisement(searchingRequestDTO.getPosition(), searchingRequestDTO.getLocation());
        List<String> urlList = new ArrayList<>();

        for (int i = 0; i < advertisementIdList.size(); i++) {

           if (clientAppService.findByApiKey(apiKey).isPresent()) { // -->> ellenőrzi az api kulcs érvényességét*/

                String id = advertisementIdList.get(i).toString();
                String url = baseUrl + "/" + id;
                urlList.add(url);

               return new ResponseEntity<>(urlList, HttpStatus.OK);
           }
        }
       return new ResponseEntity<>(HttpStatus.FORBIDDEN); //403 -->> Nem érvényes api kulcs esetén hibaüzenettel tér vissza.
    }

    @GetMapping("/advertisements")
    public List<JobAdvertisement> getAllAdvertisement(){
        return jobAdvertisementService.getAllAdvertisement();
    }
}
