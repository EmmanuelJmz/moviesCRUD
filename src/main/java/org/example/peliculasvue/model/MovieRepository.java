package org.example.peliculasvue.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Movie>{
    Optional<Movie> findByName(String movie);

    Optional<Movie> findById(Integer id);
}
