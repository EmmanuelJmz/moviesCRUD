package org.example.peliculasvue.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.peliculasvue.dto.MovieRequestDTO;
import org.example.peliculasvue.dto.MovieResponseDTO;
import org.example.peliculasvue.entity.Movie;


public class ValueMapper {
    public static Movie convertToEntity(MovieRequestDTO movieRequestDTO){
        Movie movie = new Movie();
        movie.setName(movieRequestDTO.getName());
        movie.setDirector(movieRequestDTO.getDirector());
        movie.setDuration(movieRequestDTO.getDuration());
        movie.setGender(movieRequestDTO.getGender());
        movie.setCategory(movieRequestDTO.getCategory());
        movie.setPublishDate(movieRequestDTO.getPublishDate());
        movie.setUrlImage(movieRequestDTO.getUrlImage());
        return movie;
    }

    public static MovieResponseDTO convertToDTO(Movie movie){
        MovieResponseDTO movieResponseDTO = new MovieResponseDTO();
        movieResponseDTO.setId(movie.getId());
        movieResponseDTO.setName(movie.getName());
        movieResponseDTO.setDirector(movie.getDirector());
        movieResponseDTO.setDuration(movie.getDuration());
        movieResponseDTO.setGender(movie.getGender());
        movieResponseDTO.setCategory(movie.getCategory());
        movieResponseDTO.setPublishDate(movie.getPublishDate());
        movieResponseDTO.setUrlImage(movie.getUrlImage());
        return movieResponseDTO;
    }

    public static String jsonAsString(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
