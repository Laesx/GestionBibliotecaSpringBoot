package xanketes.gestionbibliotecaspringboot.presentador;

import xanketes.gestionbibliotecaspringboot.modelo.entidades.Categoria;

import java.util.List;

public interface VistaCategorias extends VistaCategoria{
    void setCategorias(List<Categoria> listaCategorias);
}
