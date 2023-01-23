package com.univ.backend.entities;

import jakarta.persistence.*;

@Entity
public class TeamEntity {
    @Id
    @SequenceGenerator(name = "team_id_sequence", sequenceName = "team_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_id_sequence")
    public Long id;
    public String name;
    public String role;
    public String info;
    public String image;
    public String url;
}
