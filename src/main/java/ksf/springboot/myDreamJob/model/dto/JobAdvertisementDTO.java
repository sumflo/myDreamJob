package ksf.springboot.myDreamJob.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class JobAdvertisementDTO {

    private UUID clientAppId;
    private String positionName;
    private String placeOfWork;
    private String description;

}
