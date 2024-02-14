package xanketes.gestionbibliotecaspringboot.modelo.dao;

import xanketes.gestionbibliotecaspringboot.modelo.dao.helper.LogFile;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.Prestamo;
import xanketes.gestionbibliotecaspringboot.observer.Observer;
import xanketes.gestionbibliotecaspringboot.observer.Subject;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Aquí implementaremos las reglas de negocio definidas
 * en la interfaz para trabajar con préstamos y
 * base de datos en MySQL
 * @author AGE
 * @version 2
 */
public class PrestamoDAOImpl implements PrestamoDAO, Subject {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String sqlINSERT="INSERT INTO prestamos (idLibro,idUsuario,fechaPrestamo) VALUES (?,?,?)";
    private static final String sqlUPDATE="UPDATE prestamos SET idLibro=?, idUsuario=?, fechaPrestamo=? WHERE idPrestamo = ?";
    private static final String sqlDELETE="DELETE FROM prestamos WHERE idPrestamo = ?";
    public PrestamoDAOImpl() {
    }
    @Override
    public boolean insertar(Prestamo prestamo) throws Exception {
        boolean insertado=false;


        grabaEnLogIns(prestamo,sqlINSERT);
        notifyObservers();
        return insertado;
    }
    private void grabaEnLogIns(Prestamo prestamo, String sql) throws Exception {
        /*
        sql = sql.replaceFirst("\\?",String.valueOf(prestamo.getIdLibro()));
        sql = sql.replaceFirst("\\?",String.valueOf(prestamo.getIdUsuario()));
        sql = sql.replaceFirst("\\?", prestamo.getFechaPrestamo().format(formatter));
         */
        LogFile.saveLOG(sql);
    }

    @Override
    public boolean modificar(Prestamo prestamo) throws Exception {
        boolean actualizado = false;


        grabaEnLogUpd(prestamo,sqlUPDATE);
        notifyObservers();
        return actualizado;
    }
    private void grabaEnLogUpd(Prestamo prestamo, String sql) throws Exception {
        /*
        sql = sql.replaceFirst("\\?",String.valueOf(prestamo.getIdLibro()));
        sql = sql.replaceFirst("\\?",String.valueOf(prestamo.getIdUsuario()));
        sql = sql.replaceFirst("\\?", prestamo.getFechaPrestamo().format(formatter));
        sql = sql.replaceFirst("\\?",String.valueOf(prestamo.getIdPrestamo()));

         */
        LogFile.saveLOG(sql);
    }

    @Override
    public boolean borrar(int id) throws Exception {
        boolean borrado=false;


        grabaEnLog(id,sqlDELETE);
        notifyObservers();
        return borrado;
    }
    private void grabaEnLog(int id,String sql) throws Exception {
        sql = sql.replaceFirst("\\?",String.valueOf(id));
        LogFile.saveLOG(sql);
    }

    /**
     * Este método estático devuelve todos los prestamoss de la BD,
     * este método tendremos en un futuro reimplmentarlo por rangos de x,
     * para que el rendimiento no decaiga cuando la tabla crezca
     * @return un arraylist con todos los prestamos de la BD
     * @throws Exception cualquier error asociado a la consulta sql, grabar en fichero...
     */
    @Override
    public List<Prestamo> leerAllPrestamos() throws Exception {
        return null;
    }

    /**
     * Para instanciar un objeto préstamos a partir de un id
     * @param id clave primaria de la tabla préstamos
     * @return el objeto prestamos asociado a una clave primaria
     * @throws Exception cualquier error asociado a la consulta sql, grabar en fichero, ...
     */
    @Override
    public Prestamo getPrestamo(int id) throws Exception {
        return null;
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