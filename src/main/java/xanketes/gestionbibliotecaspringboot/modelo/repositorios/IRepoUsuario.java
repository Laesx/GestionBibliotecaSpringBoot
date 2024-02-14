package xanketes.gestionbibliotecaspringboot.modelo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.EntidadUsuario;
@Repository
public interface IRepoUsuario extends CrudRepository<EntidadUsuario, Integer> {

    //si hace falta se hace define algun metodo en particular.

}
