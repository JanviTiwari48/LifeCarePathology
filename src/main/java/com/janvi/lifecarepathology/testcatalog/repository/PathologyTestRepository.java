package com.janvi.lifecarepathology.testcatalog.repository;

import com.janvi.lifecarepathology.testcatalog.entity.PathologyTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PathologyTestRepository extends JpaRepository<PathologyTest, Long> {
    List<PathologyTest> findByActiveTrue();
}