package xanketes.gestionbibliotecaspringboot.modelo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.EntidadCategoria;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.EntidadUsuario;

import java.util.List;

@Repository
public interface IRepoUsuario extends CrudRepository<EntidadUsuario, Integer> {
    List<EntidadUsuario> findByIdOrNombreOrApellidos(int id, String nombre, String apellidos);

}
