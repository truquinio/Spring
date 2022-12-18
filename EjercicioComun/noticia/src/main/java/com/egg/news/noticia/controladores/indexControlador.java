package com.egg.news.noticia.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.egg.news.noticia.entidades.Noticia;
import com.egg.news.noticia.servicios.NoticiaServicio;

@Controller
@RequestMapping("/")
public class indexControlador {
    @Autowired
    NoticiaServicio noticiaServicio;

    @GetMapping("/")
    public String index(ModelMap modelo) {
        List<Noticia> noticias = noticiaServicio.listarNoticiasFechaDescendente();
        modelo.addAttribute("noticias", noticias);
        return "index.html";
    }
}