package com.cognizant.cms.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Embedded
    private FullName fullName;

    
    @Enumerated(EnumType.STRING) 
    @Column(name = "sex", length = 10, nullable = false)
    private Gender gender;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "nick_name", length = 100)
    private String nickName;

    @Column(name = "qualification", length = 100, nullable = false)
    private String qualification;

    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "houseNo", column = @Column(name = "permanent_house_no", length = 50, nullable = false)),
        @AttributeOverride(name = "street",  column = @Column(name = "permanent_street", length = 100, nullable = false)),
        @AttributeOverride(name = "landmark",column = @Column(name = "permanent_landmark", length = 100, nullable = false)),
        @AttributeOverride(name = "city",    column = @Column(name = "permanent_city", length = 100, nullable = false)),
        @AttributeOverride(name = "state",   column = @Column(name = "permanent_state", length = 100, nullable = false)),
        @AttributeOverride(name = "pin",     column = @Column(name = "permanent_pin", length = 250, nullable = false))
    })
    private Address permanentAddress;

  
    
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "houseNo", column = @Column(name = "communication_house_no", length = 50, nullable = false)),
        @AttributeOverride(name = "street",  column = @Column(name = "communication_street", length = 100, nullable = false)),
        @AttributeOverride(name = "landmark",column = @Column(name = "communication_landmark", length = 100, nullable = false)),
        @AttributeOverride(name = "city",    column = @Column(name = "communication_city", length = 100, nullable = false)),
        @AttributeOverride(name = "state",   column = @Column(name = "communication_state", length = 100, nullable = false)),
        @AttributeOverride(name = "pin",     column = @Column(name = "communication_pin", length = 250, nullable = false))
    })
    private Address communicationAddress;

    
    
    @Column(name = "notes", length = 250)
    private String notes;
}