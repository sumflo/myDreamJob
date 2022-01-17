package ksf.springboot.myDreamJob.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @Size(min = 1, max = 100, message = "Client name must be between 1 and 100 characters") // 1-100 => not null
    @NotNull(message = "Client name cannot be empty") // just in case for safety
    private String clientName;

    @Column(nullable = false)
    @Email(message = "Incorrect email format") // I know it's not perfect, but much simpler than regex
    @NotNull(message = "Email cannot be empty")
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

    @Builder
    public ClientApp(String clientName, String email, List<JobAdvertisement> jobAdvertisements) {
        this.clientName = clientName;
        this.email = email;
        this.jobAdvertisements = jobAdvertisements;
    }

    @Builder
    public ClientApp(UUID id, Timestamp createdDate, Timestamp lastModifiedDate,
                     String clientName, String email, List<JobAdvertisement> jobAdvertisements) {
        super(id, createdDate, lastModifiedDate);
        this.clientName = clientName;
        this.email = email;
        this.jobAdvertisements = jobAdvertisements;
    }
}
