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
 * en la interfaz para trabajar con usuario y
 * base de datos en MySQL
 */
public class UsuarioDAOImpl implements UsuarioDAO, Subject {
    private static final String sqlINSERT = "INSERT INTO usuario (nombre,apellidos) VALUES (?,?)";
    private static final String sqlUPDATE = "UPDATE usuario SET nombre = ?, apellidos = ? WHERE id = ?";
    private static final String sqlDELETE = "DELETE usuario WHERE id = ?";

    private static final String URL = "http://localhost:8080/api-rest/usuarios";

    public UsuarioDAOImpl() {
    }

    @Override
    public boolean insertar(Usuario usuario) throws Exception {
        boolean insertado = SolicitudesHTTP.postRequest(URL,usuario.toJSON());

        grabaEnLogIns(usuario, sqlINSERT);
        notifyObservers();
        return insertado;
    }

    private void grabaEnLogIns(Usuario usuario, String sql) throws Exception {
       /* sql = sql.replaceFirst("\\?", usuario.getNombre());
        sql = sql.replaceFirst("\\?", usuario.getApellidos());*/
        LogFile.saveLOG(sql);
    }

    @Override
    public boolean modificar(Usuario usuario) throws Exception {
        boolean modificado = SolicitudesHTTP.putRequest(URL + "/" + usuario.getId(),usuario.toJSON());

        grabaEnLogUpd(usuario, sqlUPDATE);
        notifyObservers();
        return modificado;
    }

    private void grabaEnLogUpd(Usuario usuario, String sql) throws Exception {
       /* sql = sql.replaceFirst("\\?", usuario.getNombre());
        sql = sql.replaceFirst("\\?", usuario.getApellidos());
        sql = sql.replaceFirst("\\?", String.valueOf(usuario.getId()));*/
        LogFile.saveLOG(sql);
    }


    @Override
    public boolean borrar(int id) throws Exception {
        boolean borrado = SolicitudesHTTP.deleteRequest(URL + "/" + id);
        grabaEnLogDel(id, sqlDELETE);
        notifyObservers();
        return borrado;
    }

    private void grabaEnLogDel(int id, String sql) throws Exception {
        sql = sql.replaceFirst("\\?", String.valueOf(id));
        LogFile.saveLOG(sql);
    }

    @Override
    public List<Usuario> leerAllUsuarios() throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
        JSONArray jsonArray = SolicitudesHTTP.getRequest(URL);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonUsuario = jsonArray.getJSONObject(i);
            usuarios.add(new Usuario(jsonUsuario.getInt("id"), jsonUsuario.getString("nombre"), jsonUsuario.getString("apellidos")));
        }
        LogFile.saveLOG("SELECT * FROM usuario");
        return usuarios;
    }

    @Override
    public List<Usuario> leerUsuariosOR(int id, String nombre, String apellidos) throws Exception {

        String sql = "SELECT u FROM Usuario u";
        String where = "";
        List<Usuario> lista = null;

        String wId = "";
        if (id != 0) {
            wId = "u.id = :idUsuario";
            where = Sql.rellenaWhereOR(where, wId);
        }
        String wNombre = "";
        if (!nombre.trim().isEmpty()) {
            wNombre = "u.nombre LIKE :nombreUsuario";
            where = Sql.rellenaWhereOR(where, wNombre);
        }
        String wApellidos = "";
        if (!apellidos.trim().isEmpty()) {
            wApellidos = "u.apellidos LIKE :apellidoUsuario";
            where = Sql.rellenaWhereOR(where, wApellidos);
        }

        if (where.isEmpty())
            return leerAllUsuarios();
        else {
            sql = sql + " WHERE " + where;
            /*
            TypedQuery<Usuario> typedQuery = em.createQuery(sql, Usuario.class);

            if (!wId.isEmpty())
                typedQuery.setParameter("idUsuario", id);
            if (!wNombre.isEmpty())
                typedQuery.setParameter("nombreUsuario", nombre);
            if (!wApellidos.isEmpty())
                typedQuery.setParameter("apellidoUsuario", apellidos);

            lista = typedQuery.getResultList();

             */
        }
        LogFile.saveLOG(sql);
        return lista;
    }


    @Override
    public Usuario getUsuario(int id) throws Exception {
        JSONObject jsonUsuario = SolicitudesHTTP.getRequestObject(URL + "/" + id);
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
