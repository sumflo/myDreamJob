package ksf.springboot.myDreamJob.controllers;

import ksf.springboot.myDreamJob.model.dto.ClientAppRequestDTO;
import ksf.springboot.myDreamJob.services.ClientAppService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // preparation for frontend access
public class ClientAppController {

    private ClientAppService clientAppService;

    public ClientAppController(ClientAppService clientAppService) {
        this.clientAppService = clientAppService;
    }

    @PostMapping("/clients") //though I'd rather call it register
    public ResponseEntity<Void> register(@RequestBody ClientAppRequestDTO clientAppRequestDTO) throws Exception {
        clientAppService.register(clientAppRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK); // responseban a szerver egy api kulcsot ad vissza UUID formátumban - but what does it mean???
    }
}
