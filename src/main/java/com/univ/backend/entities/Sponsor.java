package com.univ.backend.entities;

import com.univ.backend.models.ImageData;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sponsor {

    @Id
    @SequenceGenerator(name = "sponsor_id_sequence", sequenceName = "sponsor_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sponsor_id_sequence")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "image", referencedColumnName = "id")
    private ImageData image;
    private String name;
    private String details;

    public Sponsor(String name, String details) {
        this.name = name;this.details= details;
    }
}
