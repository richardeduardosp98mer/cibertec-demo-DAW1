package pe.cibertec.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.entity.ItemLista;
import pe.cibertec.entity.ListaCompra;
import pe.cibertec.entity.Usuario;
import pe.cibertec.repository.ItemRepository;
import pe.cibertec.repository.ListaRepository;
import pe.cibertec.repository.UsuarioRepository;

import java.util.List;

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

    @PostMapping("{idLista}/agregar-ietm")
    public ResponseEntity<?> agregarLista(@PathVariable Long idLista, @RequestBody ItemLista itemLista){
        ListaCompra listaCompra = listaRepository.findById(idLista).orElse(null);
        if (itemLista == null){
            return ResponseEntity.notFound().build();
        }
        itemLista.setLista(listaCompra);
        return ResponseEntity.ok(itemRepository.save(itemLista));
    }

    @PostMapping("ietm/{idItem}/estado")
    public ResponseEntity<?> cambiarEstado(@PathVariable Long idItem, @RequestParam String estado){
        return itemRepository.findById(idItem).map(item -> {
            item.setEstado(estado);
            return ResponseEntity.ok(itemRepository.save(item));
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("usuario/{idUsuario}")
    public List<ListaCompra> historial(@PathVariable Long idUsuario){
        return listaRepository.findByUsuarioId(idUsuario);
    }

    @GetMapping("{idLista}")
    public ResponseEntity<List<ItemLista>> detalle(@PathVariable Long idLista){
        List<ItemLista> items = itemRepository.detalleLista(idLista);
        if (items.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(items);
    }

    @GetMapping("{idLista}/items")
    public ResponseEntity<List<ItemLista>> estadoLista(@RequestParam String estado, @PathVariable Long idLista ){
        List<ItemLista> items = itemRepository.estadoLista(estado, idLista);
        if (items.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(items);
    }
}
