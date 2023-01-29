package com.univ.backend.entities;

import com.univ.backend.models.ImageData;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Expertise {
    @Id
    @SequenceGenerator(name = "expertise_id_sequence", sequenceName = "expertise_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expertise_id_sequence")
    private Long id;
    private String name;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "image", referencedColumnName = "id")
    private ImageData bg;
    private String info;
    private String url;
}
