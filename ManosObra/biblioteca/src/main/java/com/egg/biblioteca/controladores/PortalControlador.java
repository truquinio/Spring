package com.egg.biblioteca.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //  @Controller = Indica al framework de Spring que la clase es de tipo controladora 
@RequestMapping("/") // @RequestMapping("/") = Configura la URL que va a escuchar a la clase controladora 
public class PortalControlador {    //localhost:8080/

  @GetMapping("/") // @GetMapping("/") = Se accede a travez de una operación GET de HTTP 
  public String index() {   //localhost:8080/
  //  Index = Primer método que se va a ejecutar cuando abramos la app en localhost 

    return "index.html"; // Retorna un String "archivo.html" que debo crear dentro de resources/templates  
  }
}