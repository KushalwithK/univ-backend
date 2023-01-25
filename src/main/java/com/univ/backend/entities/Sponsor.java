package com.univ.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sponsor {

    @Id
    @SequenceGenerator(name = "sponsor_id_sequence", sequenceName = "sponsor_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sponsor_id_sequence")
    private Long id;
    private String image;
    private String name;
    private String details;
}
