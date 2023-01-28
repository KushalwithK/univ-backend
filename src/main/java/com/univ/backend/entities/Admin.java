package com.univ.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Admin {

    @Id
    @SequenceGenerator(name = "admin_id_sequence",
            sequenceName = "admin_id_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "admin_id_sequence")
    private Long id;

    private String userName;
    private String password;
}
