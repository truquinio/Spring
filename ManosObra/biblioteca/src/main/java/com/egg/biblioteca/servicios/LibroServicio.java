package com.egg.biblioteca.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.entidades.Libro;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.repositorios.AutorRepositorio;
import com.egg.biblioteca.repositorios.EditorialRepositorio;
import com.egg.biblioteca.repositorios.LibroRepositorio;

@Service // @Service = Construir una clase Servicio que conecta a varios repositorios
public class LibroServicio {

  // Attr global  instanciado con @Autowired desde claseRepositorio
  @Autowired // @Autowired = Inyección de dependencias, vincula al JPA
  private LibroRepositorio libroRepositorio;

  @Autowired
  private AutorRepositorio autorRepositorio;

  @Autowired
  private EditorialRepositorio editorialRepositorio;

  @Autowired
  ValidacionServicio validacion;

  // MÉTODO CREAR LIBROS >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

  @Transactional // @Transactional = Si falla modificación en database hace rollback, no modifica
  public void crearLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial)
      throws MiException {

    // validarTodoLibro, desde ValidacionServicio
    validacion.validarTodoLibro(isbn, titulo, ejemplares, idAutor, idEditorial);

    // Encuentra por id Autor y Editorial, los trae y guarda en objetos
    Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
    Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);
    // Optional = Por si id existe o no y si contiene algún error

    // Objetos autor y editorial
    Autor autor = new Autor();
    Editorial editorial = new Editorial();

    // Valido si respuesta está presente
    if (respuestaAutor.isPresent()) {
      autor = respuestaAutor.get();
    }

    if (respuestaEditorial.isPresent()) {
      editorial = respuestaEditorial.get();
    }

    // Creo objeto libro
    Libro libro = new Libro();

    // Seteo los parámetros
    libro.setIsbn(isbn);
    libro.setTitulo(titulo);
    libro.setEjemplares(ejemplares);
    libro.setAlta(new Date());    //  setAlta(new Date()) = Para que se instancie nuevo objeto, con fecha actual
    libro.setAutor(autor);
    libro.setEditorial(editorial);

    // Persisto / Guardo libro en base de datos
    libroRepositorio.save(libro);
  }

  // MÉTODO LISTAR LIBROS >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

  public List<Libro> listarLibros() {

    List<Libro> libros = new ArrayList<>();

    // Encuentra libros dentro de repositorio, los mete en arraylist libros
    libros = libroRepositorio.findAll();

    return libros;
  }

  // MÉTODO MODIFICAR LIBROS  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

  @Transactional
  public void modificarLibro(Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares)
      throws MiException {

    // validarTodoLibro, desde ValidacionServicio
    validacion.validarTodoLibro(isbn, titulo, ejemplares, idAutor, idEditorial);

    // Encuentra por id Libro, Autor y Editorial, los trae y guarda en objetos
    Optional<Libro> respuestaLibro = libroRepositorio.findById(isbn);
    Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
    Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);
    // Optional = Por si id existe o no y si contiene algún error

    // Objetos autor y editorial
    Autor autor = new Autor();
    Editorial editorial = new Editorial();

    // Valida si la respuesta está presente
    if (respuestaAutor.isPresent()) {
      autor = respuestaAutor.get();
    }

    if (respuestaEditorial.isPresent()) {
      editorial = respuestaEditorial.get();
    }

    if (respuestaLibro.isPresent()) {

      // Si está presente, traigo esa respuesta
      Libro libro = respuestaLibro.get();

      // Seteo los parámetros
      libro.setTitulo(titulo);
      libro.setEjemplares(ejemplares);
      libro.setAlta(new Date());
      libro.setAutor(autor);
      libro.setEditorial(editorial);

      // Persisto / Guardo libro en base de datos
      libroRepositorio.save(libro);
    }
  }

  // MÉTODO getOne = Trae primer resultado de database que coincida con id
  public Libro getOne(Long isbn) {
  return libroRepositorio.getOne(isbn);
  }
}