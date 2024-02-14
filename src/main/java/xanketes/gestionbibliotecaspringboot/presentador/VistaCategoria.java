package xanketes.gestionbibliotecaspringboot.presentador;


import xanketes.gestionbibliotecaspringboot.modelo.entidades.Categoria;


public interface VistaCategoria {
    void lanzar();
    void setPresentador(PresentadorCategoria presentador) throws Exception;
    Categoria getCategoria();



}
