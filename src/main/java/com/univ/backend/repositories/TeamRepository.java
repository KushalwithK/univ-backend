package com.univ.backend.repositories;

import com.univ.backend.entities.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {

    public Optional<TeamEntity> findByUrl(String url);
}
