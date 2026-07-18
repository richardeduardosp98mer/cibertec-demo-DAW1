package pe.cibertec.servicie;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import pe.cibertec.repository.ProductoRepository;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    @PersistenceContext
    private EntityManager em;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }
}
