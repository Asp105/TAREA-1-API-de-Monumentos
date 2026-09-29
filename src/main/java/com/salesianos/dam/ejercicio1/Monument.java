package com.salesianos.dam.ejercicio1;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Monument {

    @Id @GeneratedValue
    private Long id;
    private String country;
    private String city;
    private Double latitude;
    private Double longitude;
    private String name;
    private String description;
    private String imagen;
    private String code;
}
