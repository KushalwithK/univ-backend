package com.univ.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expertise {
    @Id
    @SequenceGenerator(name = "expertise_id_sequence", sequenceName = "expertise_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expertise_id_sequence")
    private Long id;
    private String name;
    private String bg;
    private String info;
    private String url;
}
