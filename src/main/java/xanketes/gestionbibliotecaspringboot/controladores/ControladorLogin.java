package xanketes.gestionbibliotecaspringboot.controladores;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.EntidadLogin;

import java.util.Collections;
import java.util.Map;

/**
 * Controlador para la funcionalidad de login.
 * Ahora mismo tiene las credenciales hardcodeadas.
 */
@RestController
public class ControladorLogin {

    private final static String USER = "root";
    private final static String PASSWORD = "root";

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody EntidadLogin loginRequest, HttpSession session) {

        if (!loginRequest.getUsername().equals(USER) || !loginRequest.getPassword().equals(PASSWORD)){
            Map<String, String> error = new java.util.HashMap<>(Collections.singletonMap("error", "Failed Auth"));
            error.put("message", "Credenciales Incorrectas. Usuario no Autenticado.");
            error.put("status", "400");
            return ResponseEntity.badRequest().body(error);
        }

        return ResponseEntity.ok(Collections.singletonMap("message", "Credenciales Correctas. Usuario Autenticado."));
    }
}
