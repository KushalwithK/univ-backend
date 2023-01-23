package com.univ.backend.repositories;

import com.univ.backend.entities.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {

    public TeamEntity findByUrl(String url);
}
