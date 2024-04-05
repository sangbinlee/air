package com.topas.air.repository.oracle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirRepository extends JpaRepository<Dir, Long> {
}