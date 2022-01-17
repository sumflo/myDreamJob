package ksf.springboot.myDreamJob.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class JobAdvertisement{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar", updatable = false, nullable = false )
    private UUID id;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="clientApp_id")
    private ClientApp clientApp;

    @Column(nullable = false, columnDefinition = "varchar(50)")
    @Size(min = 1, max = 50, message = "Position name must be between 1 and 50 characters")
    @NotNull(message = "Position name cannot be empty")
    private String positionName;

    @Column(nullable = false, columnDefinition = "varchar(50)")
    @Size(min = 1, max = 50, message = "Place of work must be between 1 and 50 characters")
    @NotNull(message = "Place of work cannot be empty")
    private String placeOfWork;

    @Column
    private String description;

    @Builder
    public JobAdvertisement(ClientApp clientApp, String positionName, String placeOfWork, String description) {
        this.clientApp = clientApp;
        this.positionName = positionName;
        this.placeOfWork = placeOfWork;
        this.description = description;
    }

}
