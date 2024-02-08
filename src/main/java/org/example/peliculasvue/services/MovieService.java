package org.example.peliculasvue.services;

import lombok.RequiredArgsConstructor;
import org.example.peliculasvue.model.Movie;
import org.example.peliculasvue.model.MovieRepository;
import org.example.peliculasvue.utils.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository repository;

    @Transactional(readOnly = true)
        public ResponseEntity<Message> getAll() {
            return new ResponseEntity<>(new Message(repository.findAll(), HttpStatus.OK, "Peliculas"), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(Movie movies){
        Optional<Movie> findId = repository.findById(movies.getId());
        if(findId.isPresent()){
            return new ResponseEntity<>(new Message(HttpStatus.BAD_REQUEST, true, "Registro duplicado"), HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(new Message(repository.saveAndFlush(movies), HttpStatus.OK, "Guardado Exitosamente"), HttpStatus.OK);
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(Movie movies){
        Optional<Movie> findName = repository.findByName(movies.getName());
        if(findName.isPresent()){
            return new ResponseEntity<>(new Message(HttpStatus.BAD_REQUEST, true, "Registro duplicado"), HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(new Message(repository.saveAndFlush(movies), HttpStatus.OK, "Guardado Exitosamente"), HttpStatus.OK);
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> delete(Movie movies){
        Optional<Movie> findId = repository.findById(movies.getId());
        if(findId.isPresent()){
            repository.delete(movies);
            return new ResponseEntity<>(new Message(HttpStatus.OK, true, "Eliminado Exitosamente"), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new Message(HttpStatus.BAD_REQUEST, true, "Registro No encontrado"), HttpStatus.BAD_REQUEST);
        }
    }
}
