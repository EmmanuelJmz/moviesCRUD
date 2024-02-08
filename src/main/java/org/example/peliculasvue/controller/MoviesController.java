package org.example.peliculasvue.controller;

import lombok.RequiredArgsConstructor;
import org.example.peliculasvue.services.MovieService;
import org.example.peliculasvue.utils.Message;
import org.example.peliculasvue.utils.MoviesDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
public class MoviesController {

    private final MovieService movieService;

    @GetMapping("/movies")
    public ResponseEntity<Message> getAll(){
        return movieService.getAll();
    }

    @PostMapping("/movie")
    public ResponseEntity<Message> save(@RequestBody MoviesDTO dto){
        return movieService.save(dto.moviesDTO());
    }

    @PutMapping("/movie")
    public ResponseEntity<Message> update (@RequestBody MoviesDTO dto){
        return movieService.update(dto.moviesDTO());
    }

    @DeleteMapping("/movie/{id}")
    public ResponseEntity<Message> delete (@RequestBody MoviesDTO dto){
        return movieService.delete(dto.movieID());
    }
}
