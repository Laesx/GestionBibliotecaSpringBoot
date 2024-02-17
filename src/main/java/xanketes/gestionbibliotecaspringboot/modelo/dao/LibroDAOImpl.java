package xanketes.gestionbibliotecaspringboot.modelo.dao;

import org.json.JSONArray;
import org.json.JSONObject;
import xanketes.gestionbibliotecaspringboot.excepciones.CampoVacioExcepcion;
import xanketes.gestionbibliotecaspringboot.modelo.dao.helper.LogFile;
import xanketes.gestionbibliotecaspringboot.modelo.dao.helper.SolicitudesHTTP;
import xanketes.gestionbibliotecaspringboot.modelo.dao.helper.Sql;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.Libro;
import xanketes.gestionbibliotecaspringboot.observer.Observer;
import xanketes.gestionbibliotecaspringboot.observer.Subject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Aquí implementaremos las reglas de negocio definidas
 * en la interfaz para trabajar con libro y  base de datos en MySQL
 * @author AGE
 * @version 3
 */
public class LibroDAOImpl implements LibroDAO, Subject {
    private static final String sqlINSERT="INSERT INTO libro (nombre,autor,editorial,categoria) VALUES (?,?,?,?)";
    private static final String sqlUPDATE="UPDATE libro SET nombre=?, autor=?, editorial=?, categoria=? WHERE id = ?";
    private static final String sqlDELETE="DELETE FROM libro WHERE id = ?";
    public LibroDAOImpl() {
    }

    @Override
    public boolean insertar(Libro libro) throws Exception {
        boolean insertado = SolicitudesHTTP.postRequest("http://localhost:8080/api-rest/libros",libro.toJSON());

        notifyObservers();
        grabaEnLogIns(libro,sqlINSERT);
        return insertado;
    }

    private void grabaEnLogIns(Libro libro,String sql) throws Exception {
        sql = sql.replaceFirst("\\?",libro.getNombre());
        sql = sql.replaceFirst("\\?",libro.getAutor());
        sql = sql.replaceFirst("\\?",libro.getEditorial());
        sql = sql.replaceFirst("\\?",String.valueOf(libro.getCategoria()));
        LogFile.saveLOG(sql);
    }

    @Override
    public boolean modificar(Libro libro) throws Exception {
        boolean actualizado = SolicitudesHTTP.putRequest("http://localhost:8080/api-rest/libros",libro.toJSON());

        notifyObservers();
        grabaEnLogUpd(libro,sqlUPDATE);
        return actualizado;
    }
    private void grabaEnLogUpd(Libro libro,String sql) throws Exception {
        sql = sql.replaceFirst("\\?",libro.getNombre());
        sql = sql.replaceFirst("\\?",libro.getAutor());
        sql = sql.replaceFirst("\\?",libro.getEditorial());
        sql = sql.replaceFirst("\\?",String.valueOf(libro.getCategoria()));
        sql = sql.replaceFirst("\\?",String.valueOf(libro.getId()));
        LogFile.saveLOG(sql);
    }
    private void grabaEnLogDel(int id,String sql) throws Exception {
        sql = sql.replaceFirst("\\?",String.valueOf(id));
        LogFile.saveLOG(sql);
    }
    @Override
    public boolean borrar(int id) throws Exception {
        boolean borrado = SolicitudesHTTP.deleteRequest("http://localhost:8080/api-rest/libros/"+id);
        grabaEnLogDel(id,sqlDELETE);
        notifyObservers();
        return borrado;
    }

    @Override
    public List<Libro> leerAllLibros() throws Exception {
        List<Libro> libros=new ArrayList<>();
        JSONArray jsonArray= SolicitudesHTTP.getRequest("http://localhost:8080/api-rest/libros");
        for (int i = 0; i <jsonArray.length() ; i++) {
            JSONObject jsonLibro=jsonArray.getJSONObject(i);
            JSONObject jsonCategoria=jsonLibro.getJSONObject("categoria");
            libros.add(new Libro(jsonLibro.getInt("id"),jsonLibro.getString("nombre"),jsonLibro.getString("autor"),jsonLibro.getString("editorial"),jsonCategoria.getInt("id")));
        }
        LogFile.saveLOG("SELECT * FROM libros");
        return libros;
    }

    @Override
    public List<Libro> leerLibrosOR(int id, String nombre, String autor, String editorial, int categoria) throws Exception {
        String sql="SELECT l FROM Libro l";
        String where="";
        List<Libro> lista = null;
        //EntityManager em = HibernateUtilJPA.getEntityManager();

        String wId="";
        if (id != 0) {
            wId = "l.id = :idLibro";
            where = Sql.rellenaWhereOR(where, wId);
        }
        String wNombre="";
        if (!nombre.trim().isEmpty()) {
            wNombre = "l.nombre LIKE :nombreLibro";
            where = Sql.rellenaWhereOR(where, wNombre);
        }
        String wAutor="";
        if (!autor.trim().isEmpty()) {
            wAutor = "l.autor LIKE :autorLibro";
            where = Sql.rellenaWhereOR(where, wAutor);
        }

        String wEditorial="";
        if (!editorial.trim().isEmpty()) {
            wEditorial = "l.editorial LIKE :editorialLibro";
            where = Sql.rellenaWhereOR(where, wEditorial);
            //where = id = ? OR nombre LIKE ? OR apellidos LIKE ?
        }

        String wCategoria="";
        if (categoria != 0) {
            wCategoria = "l.categoria = :categoriaLibro";
            where = Sql.rellenaWhereOR(where, wCategoria);
        }

        if (where.isEmpty())
            return leerAllLibros();
        else {
            sql = sql + " WHERE "+where;
            // sql = SELECT .... FROM usuario WHERE .......
            //TypedQuery<Libro> typedQuery = em.createQuery(sql, Libro.class);

            /*
            if (!wId.isEmpty())
                typedQuery.setParameter("idLibro", id);
            if (!wNombre.isEmpty())
                typedQuery.setParameter("nombreLibro", nombre);
            if (!wAutor.isEmpty())
                typedQuery.setParameter("autorLibro", autor);
            if (!wEditorial.isEmpty())
                typedQuery.setParameter("editorialLibro", editorial);
            if (!wCategoria.isEmpty())
                typedQuery.setParameter("categoriaLibro", categoria);

            lista = typedQuery.getResultList();
             */
        }
        LogFile.saveLOG(sql);
        return lista;
    }

    /**
     * para instanciar un objeto libro a partir de un id
     * @param id clave primaria de la tabla libro
     * @return el objeto libro asociado a una clave primaria
     * @throws SQLException cualquier error asociado a la consulta sql
     * @throws CampoVacioExcepcion en el caso que contenga una libro con libro a null

     */
    @Override
    public Libro getLibro(int id) throws Exception {
        JSONObject jsonLibro= SolicitudesHTTP.getRequest("http://localhost:8080/api-rest/usuarios/"+id).getJSONObject(0);
        return new Libro(jsonLibro.getInt("id"),jsonLibro.getString("nombre"),jsonLibro.getString("autor"),jsonLibro.getString("editorial"),jsonLibro.getInt("categoria"));
    }

    private Observer observer;

    @Override
    public void register(Observer obj){
        if (obj == null) throw new NullPointerException("Null Observer");
        observer=obj;
    }

    @Override
    public void unregister(Observer obj) {
        observer=null;
    }

    @Override
    public void notifyObservers() throws Exception {
        if (observer!=null){
            observer.update(this);
        }
    }
}
