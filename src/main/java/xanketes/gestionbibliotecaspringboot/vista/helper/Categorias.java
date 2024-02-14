package xanketes.gestionbibliotecaspringboot.vista.helper;

import xanketes.gestionbibliotecaspringboot.modelo.dao.CategoriaDAOImpl;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.Categoria;
import xanketes.gestionbibliotecaspringboot.presentador.PresentadorCategoria;
import xanketes.gestionbibliotecaspringboot.vista.FichaCategoria;
import xanketes.gestionbibliotecaspringboot.vista.FormMain;
import xanketes.gestionbibliotecaspringboot.vista.ListaCategorias;

public class Categorias {
    public static ListaCategorias listaCategorias() throws Exception {
        CategoriaDAOImpl categoriaDAO=new CategoriaDAOImpl();
        ListaCategorias listaCategorias=new ListaCategorias();
        PresentadorCategoria presentadorCategoria=new PresentadorCategoria(categoriaDAO,listaCategorias);
        listaCategorias.setPresentador(presentadorCategoria);
        // PARTE DEL OBSERVER
        categoriaDAO.register(FormMain.getInstance());
        listaCategorias.lanzar();
        return listaCategorias;
    }

    public static FichaCategoria fichaCategoria(Categoria categoria) throws Exception {
        CategoriaDAOImpl categoriaDAO=new CategoriaDAOImpl();
        FichaCategoria fichaCategoria=new FichaCategoria(categoria);
        PresentadorCategoria presentadorCategoria=new PresentadorCategoria(categoriaDAO,fichaCategoria);
        fichaCategoria.setPresentador(presentadorCategoria);
        // PARTE DEL OBSERVER
        categoriaDAO.register(FormMain.getInstance());
        fichaCategoria.lanzar();
        return fichaCategoria;

    }
}
