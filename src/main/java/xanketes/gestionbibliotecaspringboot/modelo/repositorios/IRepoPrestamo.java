package xanketes.gestionbibliotecaspringboot.modelo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.Prestamo;

@Repository
public interface IRepoPrestamo extends CrudRepository<Prestamo, Integer>{
}
