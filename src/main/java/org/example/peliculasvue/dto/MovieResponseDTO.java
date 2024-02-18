package org.example.peliculasvue.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieResponseDTO {
    private Long id;
    private String name;
    private String director;
    private int duration;
    private String gender;
    private String category;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate;
    private String urlImage;
}
