package ksf.springboot.myDreamJob.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientAppRequestDTO {

    private String clientName;
    private String email;

}
