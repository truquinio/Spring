package com.egg.biblioteca.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.egg.biblioteca.entidades.Autor;


@Repository //  @Repository = Indica que es una clase repositorio
public interface AutorRepositorio extends JpaRepository<Autor, String> {
    //  JpaRepository<Autor, String> = Extiende de repositorio JPA y maneja entidad Autor, id = String>
}