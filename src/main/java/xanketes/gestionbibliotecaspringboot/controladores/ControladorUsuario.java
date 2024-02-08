package xanketes.gestionbibliotecaspringboot.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.EntidadUsuario;
import xanketes.gestionbibliotecaspringboot.modelo.repositorios.IRepoUsuario;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class ControladorUsuario {
    @Autowired
    IRepoUsuario repoUsuario;
    //lista todos los usuarios existentes. GET all
    @GetMapping
    public List<EntidadUsuario> listarUsuarios(){
        return (List<EntidadUsuario>) repoUsuario.findAll();
    }

    //Recupera un usuario por GET id
    @GetMapping("/{id}")
    public ResponseEntity<EntidadUsuario> buscarUsuarioPorId(@PathVariable(value="id") int id){
        Optional<EntidadUsuario> usuario=repoUsuario.findById(id);
        return usuario.map(entidadUsuario -> ResponseEntity.ok().body(entidadUsuario)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //insertar usuarios POST
    @PostMapping("/{id}")
    public EntidadUsuario guardarUsuario(@Validated @RequestBody EntidadUsuario usuario) {
        return repoUsuario.save(usuario);
    }

    //borrar por id DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarUsuario(@PathVariable(value="id") int id){
        Optional<EntidadUsuario> usuario= repoUsuario.findById(id);
        if(usuario.isPresent()){
            repoUsuario.deleteById(id);
            return ResponseEntity.ok().body("Usuario borrado con exito!");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    //modificar usuario a partir del id.
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@RequestBody EntidadUsuario modUsuario, @PathVariable(value="id") int id){
        Optional<EntidadUsuario> usuario=repoUsuario.findById(id);
        if(usuario.isPresent()){
            usuario.get().setNombre(modUsuario.getNombre());
            usuario.get().setApellidos(modUsuario.getApellidos());
            repoUsuario.save(usuario.get());
            return ResponseEntity.ok().body("Usuario actualizado con exito");
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
