package xanketes.gestionbibliotecaspringboot.vista.helper;


import xanketes.gestionbibliotecaspringboot.modelo.BusquedaLibro;
import xanketes.gestionbibliotecaspringboot.modelo.dao.CategoriaDAO;
import xanketes.gestionbibliotecaspringboot.modelo.dao.CategoriaDAOImpl;
import xanketes.gestionbibliotecaspringboot.modelo.dao.LibroDAOImpl;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.Libro;
import xanketes.gestionbibliotecaspringboot.presentador.PresentadorLibro;
import xanketes.gestionbibliotecaspringboot.vista.FichaLibro;
import xanketes.gestionbibliotecaspringboot.vista.FormMain;
import xanketes.gestionbibliotecaspringboot.vista.ListaLibros;
import xanketes.gestionbibliotecaspringboot.vista.SeleccionaLibro;

import java.awt.*;

public class Libros {
    public static ListaLibros listaLibros() throws Exception {
        LibroDAOImpl libroDAO=new LibroDAOImpl();
        CategoriaDAO categoriaDAO=new CategoriaDAOImpl();
        ListaLibros listaLibros=new ListaLibros();
        PresentadorLibro presentadorLibro=new PresentadorLibro(libroDAO,categoriaDAO,listaLibros);
        // PARTE DEL OBSERVER
        libroDAO.register(FormMain.getInstance());
        listaLibros.setPresentador(presentadorLibro);
        listaLibros.lanzar();
        return listaLibros;
    }

    public static SeleccionaLibro seleccionaLibro(Frame owner, String title, boolean modal, BusquedaLibro busquedaLibro) throws Exception {
        LibroDAOImpl libroDAO=new LibroDAOImpl();
        CategoriaDAO categoriaDAO=new CategoriaDAOImpl();
        SeleccionaLibro seleccionaLibro=new SeleccionaLibro(owner,title,modal,busquedaLibro);
        PresentadorLibro presentadorLibro=new PresentadorLibro(libroDAO,categoriaDAO,seleccionaLibro);
        // PARTE DEL OBSERVER
        libroDAO.register(FormMain.getInstance());
        seleccionaLibro.setPresentador(presentadorLibro);
        seleccionaLibro.lanzar();
        return seleccionaLibro;
    }

    public static FichaLibro fichaLibro(Libro libro) throws Exception {
        LibroDAOImpl libroDAO=new LibroDAOImpl();
        CategoriaDAO categoriaDAO=new CategoriaDAOImpl();
        FichaLibro fichaLibro=new FichaLibro(libro);
        PresentadorLibro presentadorLibro=new PresentadorLibro(libroDAO,categoriaDAO,fichaLibro);
        // PARTE DEL OBSERVER
        libroDAO.register(FormMain.getInstance());
        fichaLibro.setPresentador(presentadorLibro);
        fichaLibro.lanzar();
        return fichaLibro;

    }
}
