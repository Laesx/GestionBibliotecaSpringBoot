package xanketes.gestionbibliotecaspringboot.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.EntidadHistorico;
import xanketes.gestionbibliotecaspringboot.modelo.repositorios.IRepoHistorico;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-rest/historico")
public class ControladorHistorico {

    @Autowired
    IRepoHistorico repoHistorico;

    @GetMapping
    public List<EntidadHistorico> listarHistoricos() {
        return (List<EntidadHistorico>) repoHistorico.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntidadHistorico> buscarHistoricoPorId(@PathVariable(value = "id") int id) {
        Optional<EntidadHistorico> historico = repoHistorico.findById(id);
        if (historico.isPresent())
            return ResponseEntity.ok().body(historico.get());
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public EntidadHistorico guardarHistorico(@RequestBody EntidadHistorico historico) {
        return repoHistorico.save(historico);
    }
}
