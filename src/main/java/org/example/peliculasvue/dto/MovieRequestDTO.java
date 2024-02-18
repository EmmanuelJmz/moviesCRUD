package org.example.peliculasvue.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MovieRequestDTO {
    @NotBlank(message = "Nombre pelicula no puede ser Null O vacio")
    private String name;

    @NotBlank(message = "Director no puede ser null o vacio")
    private String director;

    @Min(value = 1, message = "La duración no está definida")
    private int duration;

    @NotBlank(message = "La pelicula debe tener un genero")
    private String gender;

    @NotBlank(message = "La categoria no puede ser null o vacio")
    private String category;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "La fecha de publicación no puede ser null o vacio")
    private LocalDate publishDate;

    @NotBlank(message = "La pelicula debe tener una imagen")
    private String urlImage;
}
