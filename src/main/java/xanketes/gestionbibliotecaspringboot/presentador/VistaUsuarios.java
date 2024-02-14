package xanketes.gestionbibliotecaspringboot.presentador;

import xanketes.gestionbibliotecaspringboot.modelo.entidades.Usuario;

import java.util.List;

public interface VistaUsuarios extends VistaUsuario{
    void setUsuarios(List<Usuario> listaUsuarios);
}
