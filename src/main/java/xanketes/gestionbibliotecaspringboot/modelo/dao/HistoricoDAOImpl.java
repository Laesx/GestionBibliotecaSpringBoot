package xanketes.gestionbibliotecaspringboot.modelo.dao;

import xanketes.gestionbibliotecaspringboot.modelo.dao.helper.SolicitudesHTTP;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.Historico;
import xanketes.gestionbibliotecaspringboot.observer.Observer;
import xanketes.gestionbibliotecaspringboot.observer.Subject;

public class HistoricoDAOImpl implements HistoricoDAO, Subject {

    private Historico historico;

    public HistoricoDAOImpl(Historico historico) {
        this.historico = historico;
    }

    /** Este método inserta un historico en la BD, a través de Springboot
     * @return true si se ha insertado, false en caso contrario
     * @throws Exception cualquier error asociado a la consulta http, grabar en fichero...
     */
    @Override
    public boolean insertar() throws Exception {
        boolean insertado = SolicitudesHTTP.postRequest(URL.HISTORICO, historico.toJSON());
        notifyObservers();
       return insertado;
    }

    /**
     * Este metodo inserta aquellos mensajes que son enviados al fichero LOG
     * pero que serán almacenados en la tabla Historico
     * @param msgLog mensaje enviado para añadir a la tabla Historico
     * @throws Exception cualquier error asociado a la consulta http, grabar en fichero...
     */
    public static void mensaje(String msgLog) throws Exception {
        Historico historico=new Historico();
        historico.setUser(URL.user);
        historico.setInfo(msgLog);
        new HistoricoDAOImpl(historico).insertar();
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