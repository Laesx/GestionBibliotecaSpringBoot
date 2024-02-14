package xanketes.gestionbibliotecaspringboot.modelo.repositorios;

import org.springframework.data.repository.CrudRepository;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.Libro;

public interface IRepoLibro extends CrudRepository<Libro, Integer> {

}

