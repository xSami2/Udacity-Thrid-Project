package com.udacity.jdnd.course3.critter.Entity;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@DynamicUpdate
@Data
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private long id;

    @Column(name = "customer_name")
    private String name;

    @Column(name ="customer_phoneNumber")
    private String phoneNumber;


    @OneToMany(mappedBy = "customerEntity" , cascade = CascadeType.ALL)
    List<PetEntity> petEntities;



    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;




    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
    }


}
