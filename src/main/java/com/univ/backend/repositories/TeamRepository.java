package com.univ.backend.repositories;

import com.univ.backend.entities.TeamEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {

    public Optional<TeamEntity> findByUrl(String url);

    public Optional<TeamEntity> findByName(String name);

    @Transactional
    void deleteByName(String name);
}
