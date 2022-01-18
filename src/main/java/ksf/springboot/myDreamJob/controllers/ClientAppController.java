package ksf.springboot.myDreamJob.controllers;

import ksf.springboot.myDreamJob.model.ClientApp;
import ksf.springboot.myDreamJob.model.JobAdvertisement;
import ksf.springboot.myDreamJob.model.dto.ClientAppRequestDTO;
import ksf.springboot.myDreamJob.services.ClientAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*") // preparation for frontend access
public class ClientAppController {

    private final ClientAppService clientAppService;

    @Autowired
    public ClientAppController(ClientAppService clientAppService) {
        this.clientAppService = clientAppService;
    }

    @PostMapping("/clients")
    public ResponseEntity<UUID> register(@RequestBody ClientAppRequestDTO clientAppRequestDTO) throws Exception {
        UUID apiKey = clientAppService.register(clientAppRequestDTO);
        return new ResponseEntity<>(apiKey, HttpStatus.OK);
    }

    @GetMapping("/clientApps")
    public List<ClientApp> getAllClientApp() {
        return clientAppService.getAllClientApp();
    }

}