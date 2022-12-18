package com.egg.news.noticia.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.egg.news.noticia.entidades.Noticia;

@Repository //  Indica que es una clase repositorio
public interface NoticiaRepositorio extends JpaRepository<Noticia, String> {
    //  JpaRepository<Noticia, String> = Extiende de repositorio JPA y maneja entidad Noticia, id = String>

    @Query("SELECT n FROM Noticia n ORDER BY n.alta DESC")
    
    public List<Noticia> todosDescendente();
}