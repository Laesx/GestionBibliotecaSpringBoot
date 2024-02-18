package xanketes.gestionbibliotecaspringboot.modelo.dao;

import xanketes.gestionbibliotecaspringboot.modelo.entidades.Historico;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Esta será la intefaz para manejar los tabla de log 
 * Histórico de la BD BIBLIOTECA
 */
public interface HistoricoDAO {

    /**
     * Implementaremos las instrucciones necesarias para poder
     * insertar un registro asociado a la tabla historico para algún
     * sistema gestor de BD o de ficheros
     * @return verdad en el caso de que la inserción ser realice con éxito
     * @throws Exception cualquier error asociado a la consulta http, grabar en fichero...
     */
    boolean insertar() throws Exception;
}
