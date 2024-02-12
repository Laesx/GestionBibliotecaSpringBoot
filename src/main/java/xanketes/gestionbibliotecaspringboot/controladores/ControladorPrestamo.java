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
    public ResponseEntity<EntidadPrestamo> buscarPrestamoPorId(int id){
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

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPrestamo(@PathVariable(value = "id") int id,
                                                 @Validated @RequestBody EntidadPrestamo prestamo){
        Optional<EntidadPrestamo> prestamoOptional = repoPrestamo.findById(id);
        if (prestamoOptional.isPresent()){
            EntidadPrestamo prestamoActual = prestamoOptional.get();
            prestamoActual.setFechaPrestamo(prestamo.getFechaPrestamo());
            prestamoActual.setLibro(prestamo.getLibro());
            prestamoActual.setUsuario(prestamo.getUsuario());
            return ResponseEntity.ok().body(repoPrestamo.save(prestamoActual));
        } else return ResponseEntity.notFound().build();
    }
}
