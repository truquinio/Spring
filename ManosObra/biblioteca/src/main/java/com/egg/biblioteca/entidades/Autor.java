package com.egg.biblioteca.entidades;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Autor {

  @Id
  @GeneratedValue(generator = "uuid")                   // uuid = Identificador único universal
  @GenericGenerator(name = "uuid", strategy = "uuid2")  //  strategy = Evita que se repitan dos uuid
  private String id;

  private String nombre;

  /* CONSTR */
  public Autor() {
  }

  /* G&S */
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  /* TO STRING */
  @Override
  public String toString() {
    return "id: " + id + " / Nombre: " + nombre;
  }
}