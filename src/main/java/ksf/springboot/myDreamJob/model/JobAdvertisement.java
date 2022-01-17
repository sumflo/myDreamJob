package ksf.springboot.myDreamJob.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class JobAdvertisement extends BaseEntity{

    @ManyToOne
    private ClientApp clientApp;

    @Column(nullable = false, columnDefinition = "varchar(50)")
    @Size(min = 1, max = 50, message = "Position name must be between 1 and 50 characters")
    @NotNull(message = "Position name cannot be empty")
    private String positionName;

    @Column(nullable = false, columnDefinition = "varchar(50)")
    @Size(min = 1, max = 50, message = "Place of work must be between 1 and 50 characters")
    @NotNull(message = "Place of work cannot be empty")
    private String placeOfWork;

    @Column(columnDefinition = "varchar(255)")
    @Max(value = 255, message = "Description should not be greater than 255")
    private String description;

    @Builder
    public JobAdvertisement(ClientApp clientApp, String positionName, String placeOfWork, String description) {
        this.clientApp = clientApp;
        this.positionName = positionName;
        this.placeOfWork = placeOfWork;
        this.description = description;
    }

    @Builder
    public JobAdvertisement(UUID id, Timestamp createdDate, Timestamp lastModifiedDate,
                            ClientApp clientApp, String positionName, String placeOfWork, String description) {
        super(id, createdDate, lastModifiedDate);
        this.clientApp = clientApp;
        this.positionName = positionName;
        this.placeOfWork = placeOfWork;
        this.description = description;
    }
}
