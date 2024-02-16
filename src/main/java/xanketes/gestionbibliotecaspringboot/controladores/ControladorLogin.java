package xanketes.gestionbibliotecaspringboot.controladores;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.EntidadLogin;

import java.util.Collections;
import java.util.Map;

@RestController
public class ControladorLogin {

    private final static String USER = "root";
    private final static String PASSWORD = "root";

    /*
    private final AuthenticationManager authenticationManager;

    @Autowired
    public ControladorLogin(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

     */

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody EntidadLogin loginRequest, HttpSession session) {

        /*
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Save the security context in the session
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
         */

        /*
        if (!loginRequest.getUsername().equals(USER) || !loginRequest.getPassword().equals(PASSWORD)){
            return ResponseEntity.badRequest().body("Credenciales Incorrectas. Usuario no Autenticado.");
        }

        return ResponseEntity.ok("Credenciales Correctas. Usuario Autenticado.");
        */

        if (!loginRequest.getUsername().equals(USER) || !loginRequest.getPassword().equals(PASSWORD)){
            Map<String, String> error = new java.util.HashMap<>(Collections.singletonMap("error", "Failed Auth"));
            error.put("message", "Credenciales Incorrectas. Usuario no Autenticado.");
            error.put("status", "400");
            return ResponseEntity.badRequest().body(error);
        }

        return ResponseEntity.ok(Collections.singletonMap("message", "Credenciales Correctas. Usuario Autenticado."));
    }
}
