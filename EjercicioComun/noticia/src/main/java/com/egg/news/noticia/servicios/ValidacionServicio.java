package com.egg.news.noticia.servicios;

import org.springframework.stereotype.Service;
import com.egg.news.noticia.excepciones.MiException;

@Service
public class ValidacionServicio {

    public void validarIdNoticia(String idNoticia) throws MiException {

        if (idNoticia.isEmpty() || idNoticia == null) {
            throw new MiException("ID Noticia no puede ser vacio ni nulo");
        }
    }

    public void validarTitulo(String titulo) throws MiException {
        if (titulo.isEmpty() || titulo == null) {
            throw new MiException("Titulo no puede ser vacio ni nulo");
        }
    }

    public void validarCuerpo(String cuerpo) throws MiException {
        if (cuerpo.isEmpty() || cuerpo == null) {
            throw new MiException("Cuerpo no puede ser vacio ni nulo");
        }
    }

    public void validarNoticia(String titulo, String cuerpo) throws MiException {
        validarTitulo(titulo);
        validarCuerpo(cuerpo);
    }
}