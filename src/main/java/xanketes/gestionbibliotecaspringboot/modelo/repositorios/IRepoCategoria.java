package xanketes.gestionbibliotecaspringboot.modelo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.EntidadCategoria;

@Repository
public interface IRepoCategoria extends CrudRepository<EntidadCategoria, Integer>{
    EntidadCategoria findByCategoria(String nomCategoria);
}
