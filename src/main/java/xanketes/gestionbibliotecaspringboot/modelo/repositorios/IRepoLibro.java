package xanketes.gestionbibliotecaspringboot.modelo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.EntidadCategoria;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.EntidadLibro;

import java.util.List;

@Repository
public interface IRepoLibro extends CrudRepository<EntidadLibro, Integer> {

    //List<EntidadLibro> findByAutorOrNombreOrEditorialOrId(String autor, String nombre, String editorial, int id);
    List<EntidadLibro> findByIdOrNombreOrAutorOrEditorialOrCategoria(int id, String nombre, String autor, String editorial, EntidadCategoria categoria);
}

