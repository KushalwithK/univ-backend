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
public class TeamEntity {
    @Id
    @SequenceGenerator(name = "team_id_sequence", sequenceName = "team_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_id_sequence")
    private Long id;
    private String name;
    private String role;
    private String info;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "image", referencedColumnName = "id")
    private ImageData image;
    private String url;
}
