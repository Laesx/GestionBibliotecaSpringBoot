package xanketes.gestionbibliotecaspringboot.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.EntidadCategoria;
import xanketes.gestionbibliotecaspringboot.modelo.repositorios.IRepoCategoria;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-rest/categorias")
public class ControladorCategoria {
    @Autowired
    private IRepoCategoria repoCategoria;

    @GetMapping
    public List<EntidadCategoria> listarCategorias(){
        return (List<EntidadCategoria>) repoCategoria.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntidadCategoria> buscarCategoriaPorId(@PathVariable(value = "id") int id){
        Optional<EntidadCategoria> categoria = repoCategoria.findById(id);
        if (categoria.isPresent())
            return ResponseEntity.ok().body(categoria.get()); // HTTP 200 OK
        else return ResponseEntity.notFound().build(); // HTTP 404 Not Found
    }

    // TODO COMPROBAR SI TENDRIA QUE DEVOLVER OPTIONAL O NO EN CASO DE QUE NO EXISTA
    @GetMapping("/nombre/{nomCategoria}")
    public ResponseEntity<EntidadCategoria> buscarCategoriaPorNombre(@PathVariable(value = "nomCategoria") String nomCategoria){
        EntidadCategoria categoria = repoCategoria.findByCategoria(nomCategoria);
        if (categoria != null)
            return ResponseEntity.ok().body(categoria); // HTTP 200 OK
        else return ResponseEntity.notFound().build(); // HTTP 404 Not Found
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarCategoria(@PathVariable(value = "id") int id){
        Optional<EntidadCategoria> categoria = repoCategoria.findById(id);
        if (categoria.isPresent()){
            repoCategoria.deleteById(id);
            return ResponseEntity.ok().body("Borrada."); // HTTP 200 OK
        } else return ResponseEntity.notFound().build(); // HTTP 404 Not Found
    }

    @Validated
    @PostMapping
    public ResponseEntity<?> guardarCategoria(@Validated @RequestBody EntidadCategoria categoria){
        if (categoria.getCategoria() == null) {
            return ResponseEntity.badRequest().body("Nombre de categoria invalido.");
        }
        EntidadCategoria savedCategoria = repoCategoria.save(categoria);
        return ResponseEntity.ok(savedCategoria);
    }

    //Corregido, he eliminado el parametro id del metodo, para que busque la categoria por la id de la nueva categoria, ya que
    //queremos editar todo menos la id de esa categoria
    @PutMapping
    public ResponseEntity<?> actualizarCategoria(@Validated @RequestBody EntidadCategoria nuevacategoria){
        Optional<EntidadCategoria> categoria = repoCategoria.findById(nuevacategoria.getId());

        if (categoria.isPresent()){
            categoria.get().setCategoria(nuevacategoria.getCategoria());
            return ResponseEntity.ok().body(repoCategoria.save(categoria.get()));
        } else return ResponseEntity.notFound().build();
    }
}
