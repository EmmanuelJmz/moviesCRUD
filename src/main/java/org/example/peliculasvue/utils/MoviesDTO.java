package org.example.peliculasvue.utils;

import org.example.peliculasvue.model.Movie;

public class MoviesDTO {
    private Integer id;
    private String name;
    private Integer duration;
    private String gender;

    public Movie moviesDTO(){
        return new Movie(id, name, duration, gender);
    }
    public Movie movieID(){
        return new Movie(id);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
