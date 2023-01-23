package com.univ.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamEntity {
    @Id
    @SequenceGenerator(name = "team_id_sequence", sequenceName = "team_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_id_sequence")
    private Long id;
    private String name;
    private String role;
    private String info;
    private String image;
    private String url;
}
