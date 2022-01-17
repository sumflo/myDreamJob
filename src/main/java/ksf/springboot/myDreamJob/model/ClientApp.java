package ksf.springboot.myDreamJob.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class ClientApp{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar", updatable = false, nullable = false ) //A UUID is made up of hex digits  (4 chars each) along with 4 “-” symbols, which make its length equal to 36 characters. (128-bit long value)
    private UUID id;

    @CreationTimestamp // I think this is important because of traceability
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp // I think this is important because of traceability
    private Timestamp lastModifiedDate;

    @Column(length = 36, columnDefinition = "varchar")
    private UUID apiKey;

    @Column(nullable = false, columnDefinition = "varchar(100)")
    @Size(min = 1, max = 100, message = "Client name must be between 1 and 100 characters") // 1-100 => not null
    @NotNull(message = "Client name cannot be empty") // just in case for safety
    private String clientName;

    @Column(nullable = false)
    @Email(message = "Incorrect email format") // I know it's not perfect, but much simpler than regex
    @NotNull(message = "Email cannot be empty")
    private String email;

    @JsonManagedReference
    @OneToMany(mappedBy="clientApp", cascade = CascadeType.ALL)
    private List<JobAdvertisement> jobAdvertisements; // I could have even used Set, but I prefer List

    public ClientApp(UUID apiKey, String clientName, String email, List<JobAdvertisement> jobAdvertisements) {
        this.apiKey = apiKey;
        this.clientName = clientName;
        this.email = email;
        this.jobAdvertisements = jobAdvertisements;
    }
}
