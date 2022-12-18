package com.egg.biblioteca.servicios;

import org.springframework.stereotype.Service;

import com.egg.biblioteca.excepciones.MiException;

//  Clase creada solo para validar todos los parámetros/attr de Autor, Editorial y Libro.
//  En caso de que los valores sean nulos o vacíos, muestro una excepción personalizada

@Service    //  @Service = Para que funcione el @Autowired debe ser @Controller, @Service, @repository o @RestController
public class ValidacionServicio {

  // AUTOR + EDITORIAL (comparten id + nombre)  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
  public void validarId(String id) throws MiException {

    if (id.isEmpty() || id == null) {
      throw new MiException("id no puede estar nulo / vacío");
    }
  }

  public void validarNombre(String nombre) throws MiException {

    if (nombre.isEmpty() || nombre == null) {
      throw new MiException("El nombre no puede estar nulo / vacío");
    }
  }

  // AUTOR  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
  public void validarIdAutor(String idAutor) throws MiException {

    if (idAutor.isEmpty() || idAutor == null) {
      throw new MiException("id Autor no puede estar nulo / vacío");
    }
  }

  public void validarTodoAutor(String id, String nombre) throws MiException {

    validarId(id);
    validarNombre(nombre);
  }

  // EDITORIAL  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
  public void validarIdEditorial(String idEditorial) throws MiException {

    if (idEditorial.isEmpty() || idEditorial == null) {
      throw new MiException("id Editorial no puede estar nulo / vacío");
    }
  }

  public void validarTodoEditorial(String id, String nombre) throws MiException {

    validarId(id);
    validarNombre(nombre);
  }

  //  LIBRO >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
  public void validarIsbn(Long isbn) throws MiException {

    if (isbn == null) {
      throw new MiException("ISBN no puede ser nulo");
    }
  }

  public void validarTitulo(String titulo) throws MiException {

    if (titulo.isEmpty() || titulo == null) {
      throw new MiException("El título del libro no puede estar nulo / vacío");
    }
  }

  public void validarEjemplares(Integer ejemplares) throws MiException {

    if (ejemplares == null) {
      throw new MiException("N° de ejemplares no puede ser nulo");
    }
  }

  public void validarTodoLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial)
      throws MiException {

    //  Llamo a todos los métodos anteriores
    validarIsbn(isbn);
    validarTitulo(titulo);
    validarEjemplares(ejemplares);
    validarIdAutor(idAutor);
    validarIdEditorial(idEditorial);
  }
}