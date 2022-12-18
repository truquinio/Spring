package com.egg.news.noticia.controladores;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.news.noticia.excepciones.MiException;
import com.egg.news.noticia.servicios.NoticiaServicio;


@Controller
@RequestMapping("/noticia")
public class NoticiaControlador {
    @Autowired
    NoticiaServicio noticiaServicio;

    @GetMapping("/crear")
    public String crear() {
        return "crearNoticia.html";
    }

    @PostMapping("/creando")
    public String creando(@RequestParam String titulo, @RequestParam String cuerpo) throws MiException {
        try {
            noticiaServicio.crearNoticia(titulo, cuerpo);
            return "redirect:/admin";
        } catch (MiException e) {
            Logger.getLogger(NoticiaControlador.class.getName()).log(Level.SEVERE, null, e);
            return "crearNoticia.html";
        }
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {
        modelo.put("noticia", noticiaServicio.getOne(id));
        return "modificarNoticia.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, String titulo, String cuerpo, ModelMap modelo)
            throws MiException {
        try {
            noticiaServicio.modificarNoticia(id, titulo, cuerpo);
            return "redirect:/admin";
        } catch (MiException e) {
            modelo.put("error", e.getMessage());
            return "modificarNoticia.html";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, ModelMap modelo) {
        try {
            noticiaServicio.eliminarNoticia(id);
        } catch (MiException e) {
            modelo.put("error", e.getMessage());
        }
        return "redirect:/admin";
    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable String id, ModelMap modelo) {
        try {
            noticiaServicio.eliminarNoticia(id);
        } catch (MiException e) {
            modelo.put("error", e.getMessage());
        }
        return "redirect:/admin";
    }
}