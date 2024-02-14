package xanketes.gestionbibliotecaspringboot.modelo.repositorios;


import org.springframework.data.repository.CrudRepository;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.EntidadHistorico;
public interface IRepoHistorico extends CrudRepository<EntidadHistorico, Integer> {
}
