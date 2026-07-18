package pe.cibertec.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.entity.Producto;
import pe.cibertec.servicie.ProductoService;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
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
}
