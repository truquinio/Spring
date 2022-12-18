package com.egg.biblioteca.servicios;

import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.repositorios.EditorialRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // @Service = Construir una clase Servicio que conecta a varios repositorios
public class EditorialServicio {

  // Attr global  instanciado con @Autowired desde claseRepositorio
  @Autowired // @Autowired = Inyección de dependencias, vincula al JPA
  EditorialRepositorio editorialRepositorio;

  @Autowired
  ValidacionServicio validacion;

  // MÉTODO CREAR EDITORIALES >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

  @Transactional // @Transactional = Si falla modificación en database hace rollback, no modifica
  public void crearEditorial(String nombre) throws MiException {

    // validarNombre, desde ValidacionServicio
    validacion.validarNombre(nombre);

    // Objeto editorial
    Editorial editorial = new Editorial();

    // Seteo nombre
    editorial.setNombre(nombre);

    // Persisto / Guardo editorial en base de datos
    editorialRepositorio.save(editorial);
  }

  // MÉTODO LISTAR EDITORIALES  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

  @Transactional(readOnly = true)
  public List<Editorial> listarEditoriales() {

    List<Editorial> editoriales = new ArrayList<>();

    // Encuentra autores dentro de database, los mete en arraylist editoriales
    editoriales = editorialRepositorio.findAll();

    return editoriales;
  }

    // MÉTODO MODIFICAR EDITORIALES >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

  @Transactional
  public void modificarEditorial(String id, String nombre) throws MiException {

    // validarTodoEditorial, desde ValidacionServicio
    validacion.validarTodoEditorial(id, nombre);

    Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(id);
    // Optional = Por si id existe o no y si contiene algún error

    // Valida si la respuesta está presente
    if (respuestaEditorial.isPresent()) {

      // Si está presente, traigo esa respuesta
      Editorial editorial = respuestaEditorial.get();

      // Seteo nombre
      editorial.setNombre(nombre);

      // Persisto / Guardo editorial en base de datos
      editorialRepositorio.save(editorial);
    }
  }

  //  MÉTODO ELIMINAR EDITORIALES  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

  @Transactional
  public void eliminar(String id) throws MiException {
    Editorial editorial = editorialRepositorio.getById(id);
    editorialRepositorio.delete(editorial);
  }


  // MÉTODO getOne = Trae primer resultado de database que coincida con id

  public Editorial getOne(String id) {
    return editorialRepositorio.getOne(id);
  }
}