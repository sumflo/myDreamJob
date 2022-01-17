package ksf.springboot.myDreamJob.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class ClientApp extends BaseEntity{

    /*@Column(length = 36, columnDefinition = "varchar")
    private UUID apiKey;*/

    @Column(nullable = false, columnDefinition = "varchar(100)")
    private String clientName;

    @Column(nullable = false)
    @Email(message = "Email should be valid") // I know it's not perfect, but much simpler than regex
    private String email;

    @OneToMany(mappedBy = "clientApp")
    private List<JobAdvertisement> jobAdvertisements; // I could have even used Set, but I prefer List

/*    @Builder
    public ClientApp(UUID id, Timestamp createdDate, Timestamp lastModifiedDate,
                     UUID apiKey, String clientName, String email, List<JobAdvertisement> jobAdvertisements) {
        super(id, createdDate, lastModifiedDate);
        this.apiKey = apiKey;
        this.clientName = clientName;
        this.email = email;
        this.jobAdvertisements = jobAdvertisements;
    }*/

    public ClientApp(UUID id, Timestamp createdDate, Timestamp lastModifiedDate,
                     String clientName, String email, List<JobAdvertisement> jobAdvertisements) {
        super(id, createdDate, lastModifiedDate);
        this.clientName = clientName;
        this.email = email;
        this.jobAdvertisements = jobAdvertisements;
    }
}
