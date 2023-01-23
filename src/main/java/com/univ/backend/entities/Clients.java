package com.univ.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clients {

    @Id
    @SequenceGenerator(name = "clients_id_sequence", sequenceName = "clients_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clients_id_sequence")
    private Long id;
    private String name;
    private String logo;
}
