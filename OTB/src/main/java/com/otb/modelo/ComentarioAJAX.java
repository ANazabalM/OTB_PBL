package com.otb.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioAJAX {
    private Long comentarioId;
    private String contenido;
    private String username;
    private String email;
}
