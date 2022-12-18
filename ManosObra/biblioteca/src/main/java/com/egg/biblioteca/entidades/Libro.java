package com.egg.biblioteca.entidades;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Libro {

  @Id
  private Long isbn;
  private String titulo;
  private Integer ejemplares;

  @Temporal(TemporalType.DATE)  //  @Temporal(TemporalType.DATE) = Maneja fechas en días
  private Date alta;

  @ManyToOne  // ManyToIne = Se relaciona muchos a uno
  private Autor autor;

  @ManyToOne
  private Editorial editorial;

  /*  CONSTR  */
  public Libro() {
  }

    /*  G&S */
  public Long getIsbn() {
    return isbn;
  }

  public void setIsbn(Long isbn) {
    this.isbn = isbn;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public Integer getEjemplares() {
    return ejemplares;
  }

  public void setEjemplares(Integer ejemplares) {
    this.ejemplares = ejemplares;
  }

  public Date getAlta() {
    return alta;
  }

  public void setAlta(java.util.Date date) {
    this.alta = date;
  }

  public Autor getAutor() {
    return autor;
  }

  public void setAutor(Autor autor) {
    this.autor = autor;
  }

  public Editorial getEditorial() {
    return editorial;
  }

  public void setEditorial(Editorial editorial) {
    this.editorial = editorial;
  }

  /*  TO STRING */
  @Override
  public String toString() {
    return "Libro // isbn: " + isbn + " / Título: " + titulo + " / Ejemplares: " + ejemplares + " / Alta: " + alta + " // Autor: "
        + autor + " // Editorial: " + editorial;
  }
}