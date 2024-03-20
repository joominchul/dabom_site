package com.example.apt.repository;

import com.example.apt.entity.AptEntity;

import java.util.List;
import java.util.Optional;

public interface MybatisAptRepository {
    void save(AptEntity aptEntity);

    List<AptEntity> findAll();

    List<AptEntity> findCond(AptEntity aptEntity);
    Optional<AptEntity> findById(Long aptId);

    void update(AptEntity aptEntity);

    void delete(AptEntity aptEntity);
}
