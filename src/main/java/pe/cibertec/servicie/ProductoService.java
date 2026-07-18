package pe.cibertec.servicie;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import pe.cibertec.entity.Producto;
import pe.cibertec.repository.ProductoRepository;

import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    @PersistenceContext
    private EntityManager em;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Insercion por lotes (batch insert)
    public void registrarLote(List<Producto> productos){
        int i =0;
        for (Producto p: productos){
            em.persist(p);
            i++;
            if (i % 10 ==0){  //arma el paginado
                em.flush();
                em.clear();
            }
        }
    }
}
