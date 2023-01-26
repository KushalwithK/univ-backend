package com.univ.backend.repositories;

import com.univ.backend.models.ImageData;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDataRepository extends JpaRepository<ImageData, Long> {

    @Transactional
    void deleteByName(String name);
}
