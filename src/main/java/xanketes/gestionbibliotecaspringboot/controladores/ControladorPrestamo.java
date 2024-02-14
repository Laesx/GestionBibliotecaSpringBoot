package xanketes.gestionbibliotecaspringboot.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.EntidadPrestamo;
import xanketes.gestionbibliotecaspringboot.modelo.repositorios.IRepoPrestamo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-rest/prestamos")
public class ControladorPrestamo {
    @Autowired
    private IRepoPrestamo repoPrestamo;

    @GetMapping
    public List<EntidadPrestamo> listarPrestamos(){
        return (List<EntidadPrestamo>) repoPrestamo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntidadPrestamo> buscarPrestamoPorId(@PathVariable(value = "id") int id){
        Optional<EntidadPrestamo> prestamo = repoPrestamo.findById(id);

        if (prestamo.isPresent())
            return ResponseEntity.ok().body(prestamo.get()); // HTTP 200 OK
        else return ResponseEntity.notFound().build(); // HTTP 404 Not Found
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarPrestamo(@PathVariable(value = "id") int id){
        Optional<EntidadPrestamo> prestamo = repoPrestamo.findById(id);
        if (prestamo.isPresent()){
            repoPrestamo.deleteById(id);
            return ResponseEntity.ok().body("Borrada."); // HTTP 200 OK
        } else return ResponseEntity.notFound().build(); // HTTP 404 Not Found
    }

    @Validated
    @PostMapping
    public ResponseEntity<?> guardarPrestamo(@Validated @RequestBody EntidadPrestamo prestamo){
        EntidadPrestamo savedPrestamo = repoPrestamo.save(prestamo);
        return ResponseEntity.ok(savedPrestamo);
    }
//Corregido aqui tambien
    @PutMapping
    public ResponseEntity<?> actualizarPrestamo(@Validated @RequestBody EntidadPrestamo nuevoPrestamo){
        Optional<EntidadPrestamo> prestamo = repoPrestamo.findById(nuevoPrestamo.getIdPrestamo());

        if (prestamo.isPresent()){
            prestamo.get().setLibro(nuevoPrestamo.getLibro());
            prestamo.get().setUsuario(nuevoPrestamo.getUsuario());
            prestamo.get().setFechaPrestamo(nuevoPrestamo.getFechaPrestamo());
            repoPrestamo.save(prestamo.get());

            return ResponseEntity.ok().body(prestamo.get());
        } else return ResponseEntity.notFound().build();
    }
}
