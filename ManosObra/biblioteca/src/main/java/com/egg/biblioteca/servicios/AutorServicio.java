package com.egg.biblioteca.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.repositorios.AutorRepositorio;

@Service // @Service = Construir una clase Servicio que conecta a varios repositorios
public class AutorServicio {

  // Attr global  instanciado con @Autowired desde claseRepositorio
  @Autowired // @Autowired = Inyección de dependencias, vincula al JPA
  AutorRepositorio autorRepositorio;

  @Autowired
  ValidacionServicio validacion;

  // MÉTODO CREAR AUTORES >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

  @Transactional  // @Transactional = Si falla modificación en database hace rollback, no modifica
  public void crearAutor(String nombre) throws MiException {

    // validarNombre, desde ValidacionServicio
    validacion.validarNombre(nombre);

    // Objeto autor
    Autor autor = new Autor();

    // Seteo nombre
    autor.setNombre(nombre);

    // Persisto / Guardo autor en base de datos
    autorRepositorio.save(autor);
  }

  // MÉTODO LISTAR AUTORES  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

  public List<Autor> listarAutores() {

    List<Autor> autores = new ArrayList<>();

    // Encuentra autores dentro de database, los mete en arraylist autores
    autores = autorRepositorio.findAll();

    return autores;
  }

  // MÉTODO MODIFICAR AUTORES >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

  @Transactional
  public void modificarAutor(String id, String nombre) throws MiException {

    // validarTodoAutor, desde ValidacionServicio
    validacion.validarTodoAutor(id, nombre);

    Optional<Autor> respuestaAutor = autorRepositorio.findById(id);
    // Optional = Por si id existe o no y si contiene algún error

    // Valida  si la respuesta está presente
    if (respuestaAutor.isPresent()) {

      // Si está presente, traigo esa respuesta
      Autor autor = respuestaAutor.get();

      // Seteo nombre
      autor.setNombre(nombre);

      // Persisto / Guardo autor en base de datos
      autorRepositorio.save(autor);
    }
  }

  //  MÉTODO ELIMINAR AUTORES  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

  @Transactional
  public void eliminarAutor(String id) throws MiException {
    Autor autor = autorRepositorio.getById(id);
    autorRepositorio.delete(autor);
  }


  // MÉTODO getOne = Trae primer resultado de database que coincida con id
  
  public Autor getOne(String id) {
    return autorRepositorio.getOne(id);
  }
}
  