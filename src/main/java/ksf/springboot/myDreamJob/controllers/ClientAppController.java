package ksf.springboot.myDreamJob.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // preparation for frontend access
public class ClientAppController {
}
