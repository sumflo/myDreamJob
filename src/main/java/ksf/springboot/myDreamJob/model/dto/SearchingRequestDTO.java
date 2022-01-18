package ksf.springboot.myDreamJob.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchingRequestDTO {

    private String position;
    private String location;

}
