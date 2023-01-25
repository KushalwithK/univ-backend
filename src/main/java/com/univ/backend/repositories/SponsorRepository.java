package com.univ.backend.repositories;

import com.univ.backend.entities.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Long> {

    Optional<Sponsor> findByName(String name);

    void deleteByName(String name);
}
