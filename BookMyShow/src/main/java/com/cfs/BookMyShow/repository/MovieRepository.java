package com.cfs.BookMyShow.repository;

import com.cfs.BookMyShow.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByLanguage(String name);
    List<Movie> findByGenre(String name);
    List<Movie> findByTitleContaining(String title);
}
