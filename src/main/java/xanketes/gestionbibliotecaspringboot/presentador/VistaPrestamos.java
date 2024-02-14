package xanketes.gestionbibliotecaspringboot.presentador;

import xanketes.gestionbibliotecaspringboot.modelo.entidades.Prestamo;

import java.util.List;

public interface VistaPrestamos extends VistaPrestamo{
    void setPrestamos(List<Prestamo> listaPrestamos);
}
