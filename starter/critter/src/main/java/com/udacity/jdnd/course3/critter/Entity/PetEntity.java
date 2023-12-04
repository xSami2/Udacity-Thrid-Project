package com.udacity.jdnd.course3.critter.Entity;

import com.udacity.jdnd.course3.critter.Enum.PetTypeEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "pets")
@DynamicUpdate
@Data
public class PetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pet_id")
    private long id;

    @Column(name = "pet_type")
    @Enumerated(EnumType.STRING)
    private PetTypeEnum type;

    @Column(name = "pet_name")
    private String name;

    @Column(name = "brith_data")
    private LocalDate birthDate;

    @Column(name = "pet_notes")
    private String notes;

    @Column(name = "pet_ownerId")
    private long ownerId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;



    @ManyToOne
    @JoinColumn(name = "customer_id")
    CustomerEntity customerEntity;




    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
    }


}
