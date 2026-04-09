package com.aula.__04.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aula.__04.domain.entity.Assento;

@Repository
public interface AssentoRepository extends JpaRepository<Assento, Long>{
    
}
