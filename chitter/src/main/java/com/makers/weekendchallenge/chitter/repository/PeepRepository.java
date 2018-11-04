package com.makers.weekendchallenge.chitter.repository;

import com.makers.weekendchallenge.chitter.model.Peep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface PeepRepository extends JpaRepository<Peep, Long> {
    List<Peep> findAllByOrderByCreatedAtDesc();
}
