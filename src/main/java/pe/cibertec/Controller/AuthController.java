package pe.cibertec.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.cibertec.dto.LoginRequest;
import pe.cibertec.entity.Usuario;
import pe.cibertec.repository.UsuarioRepository;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final UsuarioRepository usuarioRepository;

    public AuthController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        Usuario usuario = usuarioRepository.findByCorreo(loginRequest.getCorreo()).orElse(null);
        if (usuario == null || !usuario.getClave().equals(loginRequest.getClave())){
            return ResponseEntity.status(401).body("Credenciales invalidas");
        }
        return ResponseEntity.ok(usuario);
    }
}
