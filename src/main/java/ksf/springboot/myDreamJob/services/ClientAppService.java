package ksf.springboot.myDreamJob.services;

import ksf.springboot.myDreamJob.model.ClientApp;
import ksf.springboot.myDreamJob.model.dto.ClientAppRequestDTO;
import ksf.springboot.myDreamJob.repositories.ClientAppRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
// @RequiredArgsConstructor
public class ClientAppService {

    private final ClientAppRepository clientAppRepository;

    @Autowired
    public ClientAppService(ClientAppRepository clientAppRepository) {
        this.clientAppRepository = clientAppRepository;
    }

    public void register(ClientAppRequestDTO clientAppRequestDTO) throws Exception{

        if(clientAppRepository.findByEmail(clientAppRequestDTO.getEmail()).isPresent()){
            throw new Exception();
        }else{
            ModelMapper modelMapper = new ModelMapper();
            ClientApp clientApp = new ClientApp();
            modelMapper.map(clientAppRequestDTO, clientApp);
            clientAppRepository.save(clientApp);

        }

    }

}
