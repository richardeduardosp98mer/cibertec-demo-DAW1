package pe.cibertec.servicie;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pe.cibertec.entity.Producto;
import pe.cibertec.repository.ProductoRepository;
import pe.cibertec.util.FormatoUtil;

import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final FormatoUtil formatoUtil;

    @PersistenceContext
    private EntityManager em;

    public ProductoService(ProductoRepository productoRepository, FormatoUtil formatoUtil) {
        this.productoRepository = productoRepository;
        this.formatoUtil = formatoUtil;
    }

    // Insercion por lotes (batch insert)
    @Transactional
    public void registrarLote(List<Producto> productos){
        int i =0;
        for (Producto p: productos){
            p.setNombre(formatoUtil.capitalizar(p.getNombre()));
            em.persist(p);
            i++;
            if (i % 10 ==0){  //arma el paginado
                em.flush();
                em.clear();
            }
        }
    }
    public List<Producto> listarTodos(){
        return em.createQuery("SELECT p FROM Producto p", Producto.class)
                .setHint("org.hibernate.fetchSize", 5) //Trae todos, pero lee de 5 en 5
                .getResultList();
    }

    public  List<Producto> buscar(String nombre){
        return productoRepository.findByNombre(nombre);
    }
}
