package xanketes.gestionbibliotecaspringboot.presentador;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.Usuario;

public interface VistaUsuario {
    void lanzar();
    void setPresentador(PresentadorUsuario presentador) throws Exception;
    Usuario getUsuario();

}
