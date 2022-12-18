package com.egg.biblioteca.repositorios;

import com.egg.biblioteca.entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //  @Repository = Indica que es una clase repositorio
public interface EditorialRepositorio extends JpaRepository<Editorial,String> {
    //  JpaRepository<Editorial, String> = Extiende de repositorio JPA y maneja entidad Editorial, id = String>
}