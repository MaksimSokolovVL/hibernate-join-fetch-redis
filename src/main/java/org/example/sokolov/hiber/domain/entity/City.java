package org.example.sokolov.hiber.domain.entity;


import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country countryId;

    @Column(name = "district")
    private String district;

    @Column(name = "population")
    private int population;

}
