package xanketes.gestionbibliotecaspringboot;

import org.json.JSONArray;
import xanketes.gestionbibliotecaspringboot.modelo.dao.PrestamoDAOImpl;
import xanketes.gestionbibliotecaspringboot.modelo.dao.helper.SolicitudesHTTP;

public class Test {
    public static void main(String[] args) {
        try {
            //Configuracion conf= Configuracion.getInstance();
            //conf.setPassword("admin1234");

            /*
            HibernateUtilJPA.getEntityManager();

            LibroDAOImpl lib = new LibroDAOImpl();
            List<Libro> libros = lib.leerAllLibros();
            System.out.println(libros);*/

            //Sql.importCSV(Libro.class,"Libro",',');

            //System.out.println(EncriptacionDesencriptacion.desencriptar("jL9QzuscGoBRMiEjVD0vHserm2Vm9F+3be04HP9Q7aZiYE1HRHXnKd8wMje+KXvX","asdf234fsdva%l9asdnklfa@f4f_adfafaAAaad;"));


            //JSONArray jsonArray = SolicitudesHTTP.getRequest("http://localhost:8080/api-rest/libros");
            //System.out.println(jsonArray.toString(6));

            PrestamoDAOImpl prestamoDAO = new PrestamoDAOImpl();
            System.out.println(prestamoDAO.leerAllPrestamos());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}