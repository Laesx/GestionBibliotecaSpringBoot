package xanketes.gestionbibliotecaspringboot.modelo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.EntidadLibro;
@Repository
public interface IRepoLibro extends CrudRepository<EntidadLibro, Integer> {

}

