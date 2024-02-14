package xanketes.gestionbibliotecaspringboot.presentador;

import xanketes.gestionbibliotecaspringboot.modelo.entidades.Libro;

import java.util.List;

public interface VistaLibros extends VistaLibro{
    void setLibros(List<Libro> listaLibros);
}
