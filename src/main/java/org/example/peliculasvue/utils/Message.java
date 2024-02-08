package org.example.peliculasvue.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Message {
    private Object data;
    private HttpStatus status;
    private boolean error;
    private String mensaje;

    public Message(Object data, HttpStatus status, String mensaje) {
        this.data = data;
        this.status = status;
        this.mensaje = mensaje;

    }

    public Message(Object data, boolean error, String mensaje) {
        this.data = data;
        this.error = error;
        this.mensaje = mensaje;
    }
}
