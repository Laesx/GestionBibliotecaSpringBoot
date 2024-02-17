package xanketes.gestionbibliotecaspringboot.helper;


import org.json.JSONObject;
import xanketes.gestionbibliotecaspringboot.modelo.dao.URL;
import xanketes.gestionbibliotecaspringboot.modelo.dao.helper.LogFile;
import xanketes.gestionbibliotecaspringboot.modelo.dao.helper.SolicitudesHTTP;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogIn {
    public static boolean logIn(String user, String password) throws Exception {
        JSONObject json = new JSONObject().put("username", user).put("password", password);
        boolean result = SolicitudesHTTP.postRequest(URL.LOGIN, json.toString());
        if (!result){
            throw new Exception("Error en el login. Credenciales incorrectas.");
        }
        String msg=String.format("Usuario conectado desde SpringBoot: %s a %s (%s)", user, URL.LOGIN,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        URL.user = user;
        LogFile.saveLOG(msg);
        return result;

    }
}
