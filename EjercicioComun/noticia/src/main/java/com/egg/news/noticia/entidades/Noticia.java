package com.egg.news.noticia.entidades;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Noticia {

  @Id
  @GeneratedValue(generator = "uuid")                   // uuid = Identificador único universal
  @GenericGenerator(name = "uuid", strategy = "uuid2")  //  strategy = Evita que se repitan dos uuid
  private String id;
  private String titulo;

  @Column(columnDefinition = "TEXT")
  private String cuerpo;

  @Temporal(TemporalType.TIMESTAMP)  //  @Temporal(TemporalType.TIMESTAMP) = Maneja fechas en días y horas
  private Date alta;

  /* CONSTR */
  public Noticia() {
  }

  /* G&S */
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getCuerpo() {
    return cuerpo;
  }

  public void setCuerpo(String cuerpo) {
    this.cuerpo = cuerpo;
  }

  public Date getAlta() {
    return alta;
  }

  public void setAlta(Date alta) {
    this.alta = alta;
  }  
}