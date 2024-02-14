package xanketes.gestionbibliotecaspringboot.modelo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.Categoria;

@Repository
public interface IRepoCategoria extends CrudRepository<Categoria, Integer>{
    Categoria findByCategoria(String nomCategoria);
}
