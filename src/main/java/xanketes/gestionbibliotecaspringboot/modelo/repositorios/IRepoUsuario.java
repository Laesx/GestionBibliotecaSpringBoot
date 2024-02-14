package xanketes.gestionbibliotecaspringboot.modelo.repositorios;

import org.springframework.data.repository.CrudRepository;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.Usuario;

public interface IRepoUsuario extends CrudRepository<Usuario, Integer> {

    //si hace falta se hace define algun metodo en particular.

}
