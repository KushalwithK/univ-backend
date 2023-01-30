package com.univ.backend.repositories;

import com.univ.backend.entities.Brand;
import com.univ.backend.entities.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findByName(String name);

    void deleteByName(String name);
}
