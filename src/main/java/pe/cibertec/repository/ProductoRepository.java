package pe.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.cibertec.entity.Producto;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByNombre(String nombre);

    @Query("SELECT p from  Producto p where p.nombre like  concat('%', :texto, '%') ")
    List<Producto> buscarPorNombre(@Param("texto") String texto);
}
