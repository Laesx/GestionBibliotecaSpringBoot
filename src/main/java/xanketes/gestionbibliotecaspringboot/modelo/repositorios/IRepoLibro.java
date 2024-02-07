package xanketes.gestionbibliotecaspringboot.modelo.repositorios;

import org.springframework.data.repository.CrudRepository;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.EntidadLibro;

public interface IRepoLibro extends CrudRepository<EntidadLibro, Integer> {

}
