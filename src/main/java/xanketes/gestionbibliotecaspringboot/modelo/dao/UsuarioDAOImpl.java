package xanketes.gestionbibliotecaspringboot.modelo.dao;

import org.json.JSONArray;
import org.json.JSONObject;
import xanketes.gestionbibliotecaspringboot.modelo.dao.helper.LogFile;
import xanketes.gestionbibliotecaspringboot.modelo.dao.helper.SolicitudesHTTP;
import xanketes.gestionbibliotecaspringboot.modelo.dao.helper.Sql;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.Usuario;
import xanketes.gestionbibliotecaspringboot.observer.Observer;
import xanketes.gestionbibliotecaspringboot.observer.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Aquí implementaremos las reglas definidas
 * en la interfaz para trabajar con las consultas de SpringBoot
 */
public class UsuarioDAOImpl implements UsuarioDAO, Subject {
    public UsuarioDAOImpl() {
    }

    /** Este método inserta un usuario en la BD, a través de Springboot
     * @param usuario objeto usuario a insertar
     * @return true si se ha insertado, false en caso contrario
     * @throws Exception cualquier error asociado a la consulta http, grabar en fichero...
     */
    @Override
    public boolean insertar(Usuario usuario) throws Exception {
        boolean insertado = SolicitudesHTTP.postRequest(URL.USUARIOS,usuario.toJSON());
        notifyObservers();
        return insertado;
    }

    /** Este método modifica un usuario en la BD, a través de Springboot
     * @param usuario objeto usuario a modificar
     * @return true si se ha modificado, false en caso contrario
     * @throws Exception cualquier error asociado a la consulta http, grabar en fichero...
     */
    @Override
    public boolean modificar(Usuario usuario) throws Exception {
        boolean modificado = SolicitudesHTTP.putRequest(URL.USUARIOS + "/" + usuario.getId(),usuario.toJSON());
        notifyObservers();
        return modificado;
    }

    /** Este método borra un usuario en la BD, a través de Springboot
     * @param id clave primaria de la tabla usuarios
     * @return true si se ha borrado, false en caso contrario
     * @throws Exception cualquier error asociado a la consulta http, grabar en fichero...
     */
    @Override
    public boolean borrar(int id) throws Exception {
        boolean borrado = SolicitudesHTTP.deleteRequest(URL.USUARIOS + "/" + id);
        notifyObservers();
        return borrado;
    }

    /** Este método devuelve todos los usuarios de la BD, a través de Springboot
     * @return un arraylist con todos los usuarios de la BD
     * @throws Exception cualquier error asociado a la consulta http, grabar en fichero...
     */
    @Override
    public List<Usuario> leerAllUsuarios() throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
        JSONArray jsonArray = SolicitudesHTTP.getRequest(URL.USUARIOS);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonUsuario = jsonArray.getJSONObject(i);
            usuarios.add(new Usuario(jsonUsuario.getInt("id"), jsonUsuario.getString("nombre"), jsonUsuario.getString("apellidos")));
        }
        return usuarios;
    }

    /** Este método devuelve una lista de usuarios de la BD, a través de Springboot
     * @param id        búsqueda de usuario con dicho código de usuario
     * @param nombre    búsqueda de usuarios con dicho nombre de usuario
     * @param apellidos búsqueda de usuarios con dichos apellidos de usuario
     * @return lista de usuarios que cumplen con los parámetros de búsqueda
     * @throws Exception cualquier error asociado a la consulta http, grabar en fichero...
     */
    @Override
    public List<Usuario> leerUsuariosOR(int id, String nombre, String apellidos) throws Exception {
        List<Usuario> lista = new ArrayList<>();
        JSONArray jsonArray = SolicitudesHTTP.getRequest(URL.USUARIOS + "/busquedaOr?id=" + id + "&nombre=" + nombre + "&apellidos=" + apellidos);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonUsuario = jsonArray.getJSONObject(i);
            lista.add(new Usuario(jsonUsuario.getInt("id"), jsonUsuario.getString("nombre"), jsonUsuario.getString("apellidos")));
        }
        return lista;
    }


    /** Este método devuelve un usuario de la BD, a través de Springboot
     * @param id clave primaria de la tabla usuario a buscar
     * @return el objeto usuario asociado a una clave primaria
     * @throws Exception cualquier error asociado a la consulta http, grabar en fichero...
     */
    @Override
    public Usuario getUsuario(int id) throws Exception {
        JSONObject jsonUsuario = SolicitudesHTTP.getRequestObject(URL.USUARIOS + "/" + id);
        return new Usuario(
                jsonUsuario.getInt("id"),
                jsonUsuario.getString("nombre"),
                jsonUsuario.getString("apellidos")
        );
    }


    private Observer observer;

    @Override
    public void register(Observer obj) {
        if (obj == null) throw new NullPointerException("Null Observer");
        observer = obj;
    }

    @Override
    public void unregister(Observer obj) {
        observer = null;
    }

    @Override
    public void notifyObservers() throws Exception {
        if (observer != null) {
            observer.update(this);
        }
    }
}
