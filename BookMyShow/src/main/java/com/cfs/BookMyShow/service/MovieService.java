package com.cfs.BookMyShow.service;


import com.cfs.BookMyShow.dto.MovieDto;
import com.cfs.BookMyShow.exception.ResourceNotFoundException;
import com.cfs.BookMyShow.model.Movie;
import com.cfs.BookMyShow.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public MovieDto createMovie(MovieDto movieDto){

        Movie movie = mapToEntity(movieDto);
        Movie saveMovie = movieRepository.save(movie);
        return mapToDto(saveMovie);
    }

    public MovieDto getMovieById(Long id){

        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));
        return mapToDto(movie);
    }

    public List<MovieDto> getAllMovies(){
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public  List<MovieDto> getMovieByLanguage(String language){

        List<Movie> movies = movieRepository.findByLanguage(language);
        return movies.stream().
                map(this::mapToDto)
                .collect(Collectors.toList());

    }

    public  List<MovieDto> getMovieByGenre(String genere){

        List<Movie> movies = movieRepository.findByGenre(genere);
        return movies.stream().
                map(this::mapToDto)
                .collect(Collectors.toList());

    }

    public  List<MovieDto> searchMovies(String title){
        List<Movie> movies = movieRepository.findByTitleContaining(title);
        return movies.stream().
                map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public MovieDto updateMovie(Long id, MovieDto movieDto){
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie Not Found"));
        movie.setTitle(movieDto.getTitle());
        movie.setGenre(movieDto.getGenre());
        movie.setLanguage(movieDto.getLanguage());
        movie.setDescription(movieDto.getDescription());
        movie.setDurationMins(movieDto.getDurationMins());
        movie.setReleaseDate(movieDto.getReleaseDate());
        movie.setPosterUrl(movieDto.getPosterUrl());
        Movie updatedMovie = movieRepository.save(movie);
        return mapToDto(updatedMovie);
    }

    public void deleteMovie(Long id){
        Movie movie= movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not Found with ID: " + id));
        movieRepository.delete(movie);

    }

    private  MovieDto mapToDto(Movie movie){
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setLanguage(movie.getLanguage());
        movieDto.setGenre(movie.getGenre());
        movieDto.setDescription(movie.getDescription());
        movieDto.setTitle(movie.getTitle());
        movieDto.setDurationMins(movie.getDurationMins());
        movieDto.setPosterUrl(movie.getPosterUrl());
        movieDto.setReleaseDate(movie.getReleaseDate());
        return movieDto;

    }

    public Movie mapToEntity(MovieDto movieDto){
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setGenre(movieDto.getGenre());
        movie.setLanguage(movieDto.getLanguage());
        movie.setDescription(movieDto.getDescription());
        movie.setDurationMins(movieDto.getDurationMins());
        movie.setReleaseDate(movieDto.getReleaseDate());
        movie.setPosterUrl(movieDto.getPosterUrl());
        return movie;
    }

}
