package xanketes.gestionbibliotecaspringboot.presentador;


import xanketes.gestionbibliotecaspringboot.modelo.entidades.Categoria;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.Prestamo;

import java.util.List;

public interface VistaPrestamo {
    void lanzar();
    void setPresentador(PresentadorPrestamo presentador) throws Exception;
    Prestamo getPrestamo();
    void setCategorias(List<Categoria> categorias);

}
