package com.egg.biblioteca.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.entidades.Libro;

import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.servicios.AutorServicio;
import com.egg.biblioteca.servicios.EditorialServicio;
import com.egg.biblioteca.servicios.LibroServicio;

@Controller // @Controller = Indica al framework de Spring que la clase es de tipo controladora
@RequestMapping("/libro") // @RequestMapping("/autor") = Configura la URL que va a escuchar a la clase
                          // controladora
public class LibroControlador {

  @Autowired // @Autowired = Inyección de dependencias, vincula al JPA
  private LibroServicio libroServicio;

  @Autowired // @Autowired = Inyección de dependencias, vincula al JPA
  private AutorServicio autorServicio;

  @Autowired // @Autowired = Inyección de dependencias, vincula al JPA
  private EditorialServicio editorialServicio;

  @GetMapping("/registrar") // @GetMapping("/registrar") = Se accede a travez de una operación GET de HTTP
  public String registrar(ModelMap modelo) { // localhost:8080/libro/registrar

    // Guardo en listas todo lo que nos trae el repositorio
    List<Autor> autores = autorServicio.listarAutores();
    List<Editorial> editoriales = editorialServicio.listarEditoriales();

    // Anclo cada lista a cada modelo
    modelo.addAttribute("autores", autores);
    modelo.addAttribute("editoriales", editoriales);

    return "libro_form.html"; // Retorna un String "archivo.html" que debo crear dentro de resources/templates
  }

  @PostMapping("/registro") // @PostMapping("/registro") = Se accede al action de HTML a travez de un método POST

  // MÉTODO REGISTRO: Recibe los parámetros llamados igual que los attr name del INPUT del HTML
  public String registro(@RequestParam(required = false) Long isbn, @RequestParam String titulo,
      @RequestParam(required = false) Integer ejemplares, @RequestParam String idAutor,
      @RequestParam String idEditorial, ModelMap modelo) {

    // @RequestParam = Indica al controlador que este parámetro va a viajar en la
    // URL y se va a ejecutar cuando se llene el formulario
    // (required=false) = Para evitar que se envíe un valor numérico nulo y se rompa
    // el código
    // ModelMap = Inserta toda la información que vamos a mostrar por pantalla /
    // interface del usuario

    // System.out.println("Nombre: " + nombre);

    try {

      // Llamo al método crearLibro, de la clase LibroServicio
      libroServicio.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);

      // En caso de cargar todo correctamente, el usuario verá el sig. mensaje, bajo
      // la llave "exito"
      modelo.put("exito", "El libro fue cargado correctamente!");

      return "index.html"; // Si todo sale bien, nos envía al index

    } catch (MiException exception) {

      List<Autor> autores = autorServicio.listarAutores();
      List<Editorial> editoriales = editorialServicio.listarEditoriales();

      modelo.addAttribute("autores", autores);
      modelo.addAttribute("editoriales", editoriales);

      // En caso de NO cargar correctamente, el usuario verá el sig. mensaje, bajo la
      // llave "error"
      modelo.put("error", exception.getMessage());

      return "libro_form.html"; // Si hay un error, se vuelve a cargar la página del formulario
    }
  }

  // LISTAR LIBROS  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
  @GetMapping("/lista")
  public String listar(ModelMap modelo) {

    List<Libro> libros = libroServicio.listarLibros();
    
    modelo.addAttribute("libros", libros);

    return "libro_list";
  }

  
  // MODIFICAR LIBROS  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

  @GetMapping("/modificar/{isbn}")
  public String modificar(@PathVariable Long isbn, ModelMap modelo) {

    modelo.put("libro", libroServicio.getOne(isbn));

    List<Autor> autores = autorServicio.listarAutores();
    List<Editorial> editoriales = editorialServicio.listarEditoriales();

    modelo.addAttribute("autores", autores);
    modelo.addAttribute("editoriales", editoriales);

    return "libro_modificar.html";
  }

  @PostMapping("/modificar/{isbn}")
  public String modificar(@PathVariable Long isbn, String titulo, Integer ejemplares, String idAutor,
      String idEditorial, ModelMap modelo) {

    try {
      List<Autor> autores = autorServicio.listarAutores();
      List<Editorial> editoriales = editorialServicio.listarEditoriales();

      modelo.addAttribute("autores", autores);
      modelo.addAttribute("editoriales", editoriales);

      libroServicio.modificarLibro(isbn, titulo, idAutor, idEditorial, ejemplares);

      return "redirect:../lista";

    } catch (MiException ex) {

      List<Autor> autores = autorServicio.listarAutores();
      List<Editorial> editoriales = editorialServicio.listarEditoriales();

      modelo.put("error", ex.getMessage());

      modelo.addAttribute("autores", autores);
      modelo.addAttribute("editoriales", editoriales);

      return "libro_modificar.html";
    }
  }
}
