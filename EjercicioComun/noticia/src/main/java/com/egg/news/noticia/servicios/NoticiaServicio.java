package com.egg.news.noticia.servicios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.news.noticia.entidades.Noticia;
import com.egg.news.noticia.excepciones.MiException;
import com.egg.news.noticia.repositorios.NoticiaRepositorio;

@Service // @Service = Construir una clase Servicio que conecta a varios repositorios
public class NoticiaServicio {

  // Attr global instanciada con @Autowired desde claseRepositorio
  @Autowired // @Autowired = Inyección de dependencias, vincula al JP
  private NoticiaRepositorio noticiaRepositorio;

  @Autowired
  private ValidacionServicio validacion;

  public Noticia getOne(String id) {
    return noticiaRepositorio.getOne(id);
  }


  // MÉTODO CREAR NOTICIAS >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

  @Transactional // @Transactional = Si falla modificación en database hace rollback, no modifica
  public void crearNoticia(String titulo, String cuerpo) throws MiException {

    // Valido titulo, cuerpo que no estén nulos o vacíos
    validacion.validarNoticia(titulo, cuerpo);

    // Creo objeto noticia
    Noticia noticia = new Noticia();

    // Seteo los parámetros
    noticia.setTitulo(titulo);
    noticia.setCuerpo(cuerpo);
    noticia.setAlta(new Date()); // setAlta(new Date()) = Para que se instancie nuevo objeto, con fecha actual

    // Persisto / Guardo noticia en base de datos
    noticiaRepositorio.save(noticia);
  }


  // MÉTODO LISTAR NOTICIAS >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
  public List<Noticia> listarNoticiasFechaDescendente(){
    
    List<Noticia> noticiasList = new ArrayList<>();

    //  Ordena la lista de Noticias de manera descendente
    noticiasList = noticiaRepositorio.findAll(Sort.by(Sort.Direction.DESC, "alta"));

    // Encuentra noticia dentro de database, los mete en arraylist noticiasList
    noticiasList = noticiaRepositorio.findAll();

    //return new Sort(Sort.Direction.ASC, "alta");
    return noticiasList;
  }


  // MÉTODO MODIFICAR NOTICIAS >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

  public void modificarNoticia(String id, String titulo, String cuerpo) throws MiException {

    // Valido id que no esté nulo o vacío
    validacion.validarIdNoticia(id);

    // Valido titulo, cuerpo que no estén nulos o vacíos
    validacion.validarNoticia(titulo, cuerpo);

    // Optional = Por si id existe o no y si contiene algún error
    Optional<Noticia> respuestaNoticia = noticiaRepositorio.findById(id);

    if (respuestaNoticia.isPresent()) {

      // Si está presente, traigo esa respuesta
      Noticia noticia = respuestaNoticia.get();

      // Seteo los parámetros
      noticia.setTitulo(titulo);
      noticia.setCuerpo(cuerpo);
      noticia.setAlta(new Date()); // setAlta(new Date()) = Para que se instancie nuevo objeto, con fecha actual

      // Persisto / Guardo noticia en base de datos
      noticiaRepositorio.save(noticia);
    }
  }


  //  MÉTODO ELIMINAR NOTICIA  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

  @Transactional
  public void eliminarNoticia(String id) throws MiException {

    // Valido id que no esté nulo o vacío
    validacion.validarIdNoticia(id);

    //  Trae el id desde database y lo guarda en objeto
    Noticia noticia = noticiaRepositorio.getById(id);

    // Persisto / Guardo noticia en base de datos
    noticiaRepositorio.delete(noticia);
  }
}