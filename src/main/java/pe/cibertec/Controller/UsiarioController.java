package pe.cibertec.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.entity.Usuario;
import pe.cibertec.repository.UsuarioRepository;
import pe.cibertec.servicie.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsiarioController {
    private final UsuarioService usuarioService;

    //Constructur para inicializar repository
    public UsiarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    //Funcion para registrar un nuevo usario
    @PostMapping("/registrar")
    //ResponseEntity es la respuesta que guarda el registro
    public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario){
        Usuario guardado = usuarioService.registrar(usuario);
        return ResponseEntity.ok(guardado);
    }

    // Funcion para mopstrar todos los usuarios registrados
    @GetMapping
    public List<Usuario> listar(UsuarioService usuarioService){
        return usuarioService.listar();
    }
}
