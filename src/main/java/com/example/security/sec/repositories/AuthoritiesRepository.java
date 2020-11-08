package com.example.security.sec.repositories;

import com.example.security.sec.model.Authorities;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthoritiesRepository extends CrudRepository <Authorities, Long> {
    List<Authorities> findAll();
}
