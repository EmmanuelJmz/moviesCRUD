package org.example.peliculasvue.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.peliculasvue.dto.MovieRequestDTO;
import org.example.peliculasvue.dto.MovieResponseDTO;
import org.example.peliculasvue.entity.Movie;
import org.example.peliculasvue.entity.MovieRepository;
import org.example.peliculasvue.exception.MovieNotFoundException;
import org.example.peliculasvue.exception.MovieServiceBusinessException;
import org.example.peliculasvue.util.ValueMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class MovieService {

    private MovieRepository movieRepository;

    public MovieResponseDTO createNewMovie(MovieRequestDTO movieRequestDTO, MultipartFile multipartFile) throws MovieServiceBusinessException {
        MovieResponseDTO movieResponseDTO;

        try {
            log.info("MovieService:CreateNewMovie execution started.");
            Movie movie = ValueMapper.convertToEntity(movieRequestDTO);
            log.debug("MovieService:CreateNewMovie request parameters {}", ValueMapper.jsonAsString(movieRequestDTO));

            Movie movieResults = movieRepository.save(movie);
            movieResponseDTO = ValueMapper.convertToDTO(movieResults);
            log.debug("MovieService:CreateNewMovie received response from Database {}", ValueMapper.jsonAsString(movieRequestDTO));

        } catch (Exception e){
            log.error("Exception occurred while persisting movie to database , Exception message {}", e.getMessage());
            throw new MovieServiceBusinessException("Exception occurred while create a new movie");
        }
        log.info("MovieService:CreateNewMovie execution ended :)");
        return movieResponseDTO;
    }

    @Cacheable(value = "movies")
    public List<MovieResponseDTO> getMovies() throws MovieServiceBusinessException{
        List<MovieResponseDTO> movieResponseDTOS = null;

        try {
            log.info("MovieService:getMovies execution started.");

            List<Movie> movieList = movieRepository.findAll();

            if (!movieList.isEmpty()){
                movieResponseDTOS = movieList.stream()
                        .map(ValueMapper::convertToDTO)
                        .collect(Collectors.toList());
            }else {
                movieResponseDTOS = Collections.emptyList();
            }
            log.debug("MovieService:getMovies retrieving movies from database  {}", ValueMapper.jsonAsString(movieResponseDTOS));

        } catch (Exception e){
            log.error("Exception ocurred while retrieving movies from database, Exception message {}", e.getMessage());
            throw new MovieServiceBusinessException("Exception occurred while fetch all movies from Database");
        }
        log.info("MovieService:getMovies execution ended");
        return movieResponseDTOS;
    }

    // Buscar peliculas por nombre que contenga el parametro
    @Cacheable(value = "movies")
    public List<MovieResponseDTO> getMoviesByNameContaining(String name) throws MovieServiceBusinessException {
        List<MovieResponseDTO> movieResponseDTOS = null;

        try {
            log.info("MovieService:getMoviesByNameContaining execution started.");

            List<Movie> movieList = movieRepository.findByNameContaining(name);

            if (!movieList.isEmpty()) {
                movieResponseDTOS = movieList.stream()
                        .map(ValueMapper::convertToDTO)
                        .collect(Collectors.toList());
            } else {
                movieResponseDTOS = Collections.emptyList();
            }
            log.debug("MovieService:getMoviesByNameContaining retrieving movies from database  {}", ValueMapper.jsonAsString(movieResponseDTOS));

        } catch (Exception e) {
            log.error("Exception ocurred while retrieving movies from database, Exception message {}", e.getMessage());
            throw new MovieServiceBusinessException("Exception occurred while fetch all movies from Database");
        }
        log.info("MovieService:getMoviesByNameContaining execution ended");
        return movieResponseDTOS;
    }

    @Cacheable(value = "movies")
    public List<MovieResponseDTO> getMoviesByDirectorContaining(String director) throws MovieServiceBusinessException{
        List<MovieResponseDTO> movieResponseDTOS = null;

        try {
            log.info("MoviesService:getMoviesByDirectorContaining execution started");
            List<Movie> movieList = movieRepository.findByDirectorContaining(director);

            if (!movieList.isEmpty()){
                movieResponseDTOS = movieList.stream()
                        .map(ValueMapper::convertToDTO)
                        .collect(Collectors.toList());
            }else {
                movieResponseDTOS = Collections.emptyList();
            }
            log.debug("MovieService:getMoviesByDirectorContaining retrieving movies from database  {}", ValueMapper.jsonAsString(movieResponseDTOS));
        }catch (Exception e){
            log.error("Exception ocurred while retrieving movies from database, Exception message {}", e.getMessage());
            throw new MovieServiceBusinessException("Exception occurred while fetch directors movies from Database");
        }
        log.info("MovieService:getMoviesByDirectorContaining execution ended");
        return movieResponseDTOS;
    }

    @Cacheable(value = "movies")
    public List<MovieResponseDTO> getMoviesByPublishDateBetween(LocalDate publishDate, LocalDate publishDate2) throws MovieServiceBusinessException {
        List<MovieResponseDTO> movieResponseDTOS = null;

        try {
            log.info("MovieService:getMoviesByPublishDateBetween execution started.");

            List<Movie> movieList = movieRepository.findByPublishDateBetween(publishDate, publishDate2);

            if (!movieList.isEmpty()) {
                movieResponseDTOS = movieList.stream()
                        .map(ValueMapper::convertToDTO)
                        .collect(Collectors.toList());
            } else {
                movieResponseDTOS = Collections.emptyList();
            }
            log.debug("MovieService:getMoviesByPublishDateBetween retrieving movies from database  {}", ValueMapper.jsonAsString(movieResponseDTOS));

        } catch (Exception e) {
            log.error("Exception ocurred while retrieving movies from database, Exception message {}", e.getMessage());
            throw new MovieServiceBusinessException("Exception occurred while fetch all movies from Database");
        }
        log.info("MovieService:getMoviesByPublishDateBetween execution ended");
        return movieResponseDTOS;
    }

    @Cacheable(value = "movies")
    public List<MovieResponseDTO> getMoviesByGenderOrCategory(String gender, String category) throws MovieServiceBusinessException {
        List<MovieResponseDTO> movieResponseDTOS = null;

        try {
            log.info("MovieService:getMoviesByGenderOrCategory execution started.");

            List<Movie> movieList = movieRepository.findByGenderOrCategory(gender, category);

            if (!movieList.isEmpty()) {
                movieResponseDTOS = movieList.stream()
                        .map(ValueMapper::convertToDTO)
                        .collect(Collectors.toList());
            } else {
                movieResponseDTOS = Collections.emptyList();
            }
            log.debug("MovieService:getMoviesByGenderOrCategory retrieving movies from database  {}", ValueMapper.jsonAsString(movieResponseDTOS));

        } catch (Exception e) {
            log.error("Exception ocurred while retrieving movies from database, Exception message {}", e.getMessage());
            throw new MovieServiceBusinessException("Exception occurred while fetch all movies from Database");
        }
        log.info("MovieService:getMoviesByGenderOrCategory execution ended");
        return movieResponseDTOS;
    }

    @Cacheable(value = "movies")
    public List<MovieResponseDTO> getMoviesOrderByPublishDateDesc() throws MovieServiceBusinessException {
        List<MovieResponseDTO> movieResponseDTOS = null;

        try {
            log.info("MovieService:getMoviesOrderByPublishDateDesc execution started.");

            List<Movie> movieList = movieRepository.findByOrderByPublishDateDesc();

            if (!movieList.isEmpty()) {
                movieResponseDTOS = movieList.stream()
                        .map(ValueMapper::convertToDTO)
                        .collect(Collectors.toList());
            } else {
                movieResponseDTOS = Collections.emptyList();
            }
            log.debug("MovieService:getMoviesOrderByPublishDateDesc retrieving movies from database  {}", ValueMapper.jsonAsString(movieResponseDTOS));

        } catch (Exception e) {
            log.error("Exception ocurred while retrieving movies from database, Exception message {}", e.getMessage());
            throw new MovieServiceBusinessException("Exception occurred while fetch all movies from Database");
        }
        log.info("MovieService:getMoviesOrderByPublishDateDesc execution ended");
        return movieResponseDTOS;
    }


    @Cacheable(value = "movies")
    public MovieResponseDTO getMovieById(long movieId){
        MovieResponseDTO movieResponseDTO;
        try {
            log.info("MovieService:getMovies execution started");

            Movie movie = movieRepository.findById(movieId)
                    .orElseThrow(() -> new MovieNotFoundException("Movie not found with id" + movieId));
            movieResponseDTO = ValueMapper.convertToDTO(movie);

            log.debug("MovieService:getMovieById retrieving product from database for id {} {}", movieId, ValueMapper.jsonAsString(movieResponseDTO));

        }catch (Exception e){
            log.error("Exception occurred while retrieving movie {} from database , Exception message {}", movieId, e.getMessage());
            throw new MovieServiceBusinessException("Exception occurred while fetch product from Database " + movieId);
        }
        log.info("MovieService:getMovieById execution ended");
        return movieResponseDTO;
    }

    public MovieResponseDTO updateMovie (long movieId, MovieRequestDTO movieRequestDTO){
        MovieResponseDTO movieResponseDTO;
        try {
            log.info("MovieService:updateMovie execution started");

            Movie movie = movieRepository.findById(movieId)
                    .orElseThrow(() -> new MovieNotFoundException("Movie not found with id" + movieId));
            movie.setName(movieRequestDTO.getName());
            movie.setDirector(movieRequestDTO.getDirector());
            movie.setDuration(movieRequestDTO.getDuration());
            movie.setGender(movieRequestDTO.getGender());
            movie.setPublishDate(movieRequestDTO.getPublishDate());

            Movie movieResults = movieRepository.save(movie);
            movieResponseDTO = ValueMapper.convertToDTO(movieResults);

            log.debug("MovieService:updateMovie updating product in database for id {} {}", movieId, ValueMapper.jsonAsString(movieResponseDTO));

        }catch (Exception e){
            log.error("Exception occurred while updating movie {} from database , Exception message {}", movieId, e.getMessage());
            throw new MovieServiceBusinessException("Exception occurred while updating product from Database " + movieId);
        }
        log.info("MovieService:updateMovie execution ended");
        return movieResponseDTO;
    }

    public void deleteMovie(long movieId){
        try {
            log.info("MovieService:deleteMovie execution started");

            Movie movie = movieRepository.findById(movieId)
                    .orElseThrow(() -> new MovieNotFoundException("Movie not found with id" + movieId));
            movieRepository.delete(movie);

            log.debug("MovieService:deleteMovie deleting product from database for id {}", movieId);

        }catch (Exception e){
            log.error("Exception occurred while deleting movie {} from database , Exception message {}", movieId, e.getMessage());
            throw new MovieServiceBusinessException("Exception occurred while deleting product from Database " + movieId);
        }
        log.info("MovieService:deleteMovie execution ended");
    }

}
