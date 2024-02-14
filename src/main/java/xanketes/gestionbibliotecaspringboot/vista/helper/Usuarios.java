package xanketes.gestionbibliotecaspringboot.vista.helper;


import xanketes.gestionbibliotecaspringboot.modelo.BusquedaUsuario;
import xanketes.gestionbibliotecaspringboot.modelo.dao.UsuarioDAOImpl;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.Usuario;
import xanketes.gestionbibliotecaspringboot.presentador.PresentadorUsuario;
import xanketes.gestionbibliotecaspringboot.vista.FichaUsuario;
import xanketes.gestionbibliotecaspringboot.vista.FormMain;
import xanketes.gestionbibliotecaspringboot.vista.ListaUsuarios;
import xanketes.gestionbibliotecaspringboot.vista.SeleccionaUsuario;

import java.awt.*;

public class Usuarios {
    public static ListaUsuarios listaUsuarios() throws Exception {
        UsuarioDAOImpl usuarioDAO=new UsuarioDAOImpl();
        ListaUsuarios listaUsuarios=new ListaUsuarios();
        PresentadorUsuario presentadorUsuario=new PresentadorUsuario(usuarioDAO,listaUsuarios);
        // PARTE DEL OBSERVER
        usuarioDAO.register(FormMain.getInstance());
        listaUsuarios.setPresentador(presentadorUsuario);
        listaUsuarios.lanzar();
        return listaUsuarios;
    }

    public static SeleccionaUsuario seleccionaUsuario(Frame owner, String title, boolean modal, BusquedaUsuario busquedaUsuario) throws Exception {
        UsuarioDAOImpl usuarioDAO=new UsuarioDAOImpl();
        SeleccionaUsuario seleccionaUsuario=new SeleccionaUsuario(owner, title, modal,busquedaUsuario);
        PresentadorUsuario presentadorUsuario=new PresentadorUsuario(usuarioDAO,seleccionaUsuario);
        seleccionaUsuario.setPresentador(presentadorUsuario);
        // PARTE DEL OBSERVER
        usuarioDAO.register(FormMain.getInstance());
        seleccionaUsuario.lanzar();
        return seleccionaUsuario;
    }

    public static FichaUsuario fichaUsuario(Usuario usuario) throws Exception {
        UsuarioDAOImpl usuarioDAO=new UsuarioDAOImpl();
        FichaUsuario fichaUsuario=new FichaUsuario(usuario);
        PresentadorUsuario presentadorUsuario=new PresentadorUsuario(usuarioDAO,fichaUsuario);
        fichaUsuario.setPresentador(presentadorUsuario);
        // PARTE DEL OBSERVER
        usuarioDAO.register(FormMain.getInstance());
        fichaUsuario.lanzar();
        return fichaUsuario;

    }
}
