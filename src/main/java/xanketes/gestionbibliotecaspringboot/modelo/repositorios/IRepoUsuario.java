package xanketes.gestionbibliotecaspringboot.modelo.repositorios;

import org.springframework.data.repository.CrudRepository;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.EntidadUsuario;

public interface IRepoUsuario extends CrudRepository<EntidadUsuario, Integer> {

    //si hace falta se hace define algun metodo en particular.

}
