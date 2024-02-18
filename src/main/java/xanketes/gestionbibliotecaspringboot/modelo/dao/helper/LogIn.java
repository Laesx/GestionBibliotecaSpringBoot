package xanketes.gestionbibliotecaspringboot.modelo.dao.helper;


import org.json.JSONObject;
import xanketes.gestionbibliotecaspringboot.modelo.dao.URL;
import xanketes.gestionbibliotecaspringboot.modelo.dao.helper.LogFile;
import xanketes.gestionbibliotecaspringboot.modelo.dao.helper.SolicitudesHTTP;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogIn {
    public static boolean logIn(String user, String password) throws Exception {
        URL.user = user;
        JSONObject json = new JSONObject().put("username", user).put("password", password);
        boolean result = SolicitudesHTTP.postRequest(URL.LOGIN, json.toString());
        if (!result){
            throw new Exception("Error en el login. Credenciales incorrectas.");
        }
        return result;
    }
}
