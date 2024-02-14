package xanketes.gestionbibliotecaspringboot.vista.helper;


import xanketes.gestionbibliotecaspringboot.modelo.dao.*;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.Prestamo;
import xanketes.gestionbibliotecaspringboot.presentador.PresentadorPrestamo;
import xanketes.gestionbibliotecaspringboot.vista.FichaPrestamo;
import xanketes.gestionbibliotecaspringboot.vista.FormMain;
import xanketes.gestionbibliotecaspringboot.vista.ListaPrestamos;

public class Prestamos {
    public static ListaPrestamos
    listaPrestamos() throws Exception {
        PrestamoDAOImpl prestamoDAO=new PrestamoDAOImpl();
        CategoriaDAO categoriaDAO=new CategoriaDAOImpl();
        ListaPrestamos listaPrestamos=new ListaPrestamos();
        PresentadorPrestamo presentadorPrestamo=new PresentadorPrestamo(prestamoDAO,categoriaDAO,listaPrestamos);
        listaPrestamos.setPresentador(presentadorPrestamo);
        // PARTE DEL OBSERVER
        prestamoDAO.register(FormMain.getInstance());
        listaPrestamos.lanzar();
        return listaPrestamos;
    }

    public static FichaPrestamo fichaPrestamo(Prestamo prestamo) throws Exception {
        PrestamoDAOImpl prestamoDAO=new PrestamoDAOImpl();
        CategoriaDAO categoriaDAO=new CategoriaDAOImpl();
        UsuarioDAO usuarioDAO= new UsuarioDAOImpl();
        LibroDAO libroDAO=new LibroDAOImpl();
        FichaPrestamo fichaPrestamo=new FichaPrestamo(prestamo);
        PresentadorPrestamo presentadorPrestamo=new PresentadorPrestamo(prestamoDAO,categoriaDAO,fichaPrestamo);
        fichaPrestamo.setPresentador(presentadorPrestamo);
        // PARTE DEL OBSERVER
        prestamoDAO.register(FormMain.getInstance());
        fichaPrestamo.lanzar();
        return fichaPrestamo;

    }
}
