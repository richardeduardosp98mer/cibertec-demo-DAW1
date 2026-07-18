package pe.cibertec.Controller;

import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.entity.Producto;
import pe.cibertec.repository.ProductoRepository;
import pe.cibertec.servicie.ProductoService;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;
    private final ProductoRepository productoRepository;

    public ProductoController(ProductoService productoService, ProductoRepository productoRepository) {
        this.productoService = productoService;
        this.productoRepository = productoRepository;
    }

    @PostMapping("/lote")
    public ResponseEntity<String> registrarLote(@RequestBody List<Producto> productos){
        productoService.registrarLote(productos);
        return ResponseEntity.ok("Productos Registrados satisfactoriamente");
    }

    @GetMapping
    public List<Producto> listar(){
        return productoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id){
        return productoRepository.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar/{nombre}")
    public List<Producto> buscarPorNombre(@PathVariable String nombre){
        return productoService.buscar(nombre);
    }
}
