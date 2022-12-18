package com.egg.biblioteca.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.egg.biblioteca.entidades.Libro;


@Repository //  @Repository = Indica que es una clase repositorio
public interface LibroRepositorio extends JpaRepository<Libro, Long> {
    //  JpaRepository<Libro, Long> = Extiende de repositorio JPA y maneja entidad Libro, id = Long>

    @Query("SELECT l FROM Libro l Where l.titulo = :titulo")    
    public Libro buscarPorTitulo(@Param("titulo") String titulo);   // @Param("titulo") = Vincula con campos de Query
    //  l.titulo = @Param("titulo") y :titulo = String titulo

    @Query("SELECT l FROM Libro l WHERE l.autor.nombre = :nombre")
    public List<Libro> buscarPorAutor(@Param("nombre") String nombre);
    //  l.autor.nombre = @Param("nombre") y :nombre = String nombre
}
