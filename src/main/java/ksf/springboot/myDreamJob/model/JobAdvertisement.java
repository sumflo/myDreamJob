package ksf.springboot.myDreamJob.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
    private String positionName;

    @Column(nullable = false, columnDefinition = "varchar(50)")
    private String placeOfWork;

    @Column(columnDefinition = "varchar(255)")
    private String description;

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
