package xanketes.gestionbibliotecaspringboot.controladores;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.EntidadLibro;
import xanketes.gestionbibliotecaspringboot.modelo.repositorios.IRepoLibro;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/libros")
public class ControladorLibro {
    @Autowired
    IRepoLibro repoLibro;

    @GetMapping
    public List<EntidadLibro> listarLibros(){return (List<EntidadLibro>) repoLibro.findAll();}

    @GetMapping("/{id}")
    public ResponseEntity<EntidadLibro> buscarLibroPorId(@PathVariable(value = "id") int id){
        Optional<EntidadLibro> libro = repoLibro.findById(id);

        if(libro.isPresent())
            return ResponseEntity.ok().body(libro.get());
        else return ResponseEntity.notFound().build();
    }

/*
    @PostMapping
    public ResponseEntity<?> guardarLibro(@Validated @RequestBody EntidadLibro libro){
        Optional<EntidadLibro> libroExistente =
    }

 */

    @PostMapping("/{id}")
    public EntidadLibro guardarLibro(@Validated @RequestBody EntidadLibro libro){
        return repoLibro.save(libro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarLibro(@PathVariable(value = "id") int id){
        Optional<EntidadLibro> libro = repoLibro.findById(id);

        if(libro.isPresent()){
            repoLibro.deleteById(id);
            return ResponseEntity.ok().body("Libro borrado con exito!");
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarLibro(@RequestBody EntidadLibro nuevoLibro, @PathVariable(value = "id") int id) {
        Optional<EntidadLibro> libro = repoLibro.findById(id);

        if (libro.isPresent()) {
            libro.get().setNombre(nuevoLibro.getNombre());
            libro.get().setAutor(nuevoLibro.getAutor());
            libro.get().setEditorial(nuevoLibro.getEditorial());
            libro.get().setCategoria(nuevoLibro.getCategoria());
            repoLibro.save(libro.get());
            return ResponseEntity.ok().body("Libro actualizado con exito!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
