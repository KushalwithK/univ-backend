package com.univ.backend.entities;

import com.univ.backend.models.ImageData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
    @Id
    @SequenceGenerator(name = "brand_id_sequence", sequenceName = "brand_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_id_sequence")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "image", referencedColumnName = "id")
    private ImageData image;
    private String name;
    private String details;

    public Brand(String name, String details) {
        this.name = name;this.details= details;
    }
}
