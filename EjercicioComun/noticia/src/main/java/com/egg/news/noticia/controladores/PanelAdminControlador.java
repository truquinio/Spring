package com.egg.news.noticia.controladores;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.egg.news.noticia.entidades.Noticia;
import com.egg.news.noticia.servicios.NoticiaServicio;

@Controller                               // @Controller = Indica al framework de Spring que la clase es tipo controladora
@RequestMapping("/admin")                 // @RequestMapping("/") = Configura URL que va a escuchar a clase controladora
public class PanelAdminControlador {

  @Autowired
  NoticiaServicio noticiaServicio;

  @GetMapping("")                         // @GetMapping("") = Se accede a travez de una operación GET de HTTP
  public String index(ModelMap modelo) {  // localhost:8080/
    
    List<Noticia> noticias = noticiaServicio.listarNoticiasFechaDescendente();

    modelo.addAttribute("noticias", noticias);

    return "panel_admin.html";  // Retorna un String "archivo.html" que debo crear dentro de resources/templates
  }
}