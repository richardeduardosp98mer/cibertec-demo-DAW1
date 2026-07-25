package pe.cibertec.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.entity.ListaCompra;
import pe.cibertec.entity.Usuario;
import pe.cibertec.repository.ItemRepository;
import pe.cibertec.repository.ListaRepository;
import pe.cibertec.repository.UsuarioRepository;

@RestController
@RequestMapping("api/listas")
public class ListaCompraController {
    private final UsuarioRepository usuarioRepository;
    private final ListaRepository listaRepository;
    private final ItemRepository itemRepository;

    public ListaCompraController(UsuarioRepository usuarioRepository, ListaRepository listaRepository, ItemRepository itemRepository) {
        this.usuarioRepository = usuarioRepository;
        this.listaRepository = listaRepository;
        this.itemRepository = itemRepository;
    }

    @PostMapping("{idUsuario}/crear")
    public ResponseEntity<?> crear(@PathVariable Long idUsuario, @RequestBody ListaCompra listaCompra){
        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
        if (usuario == null){
            return ResponseEntity.badRequest().body("Usuario no encontrado");
        }
        listaCompra.setUsuario(usuario);
        return  ResponseEntity.ok(listaRepository.save(listaCompra));
    }
}
