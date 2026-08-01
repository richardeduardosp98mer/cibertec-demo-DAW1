package pe.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.cibertec.entity.ListaCompra;

import java.util.List;

public interface ListaRepository extends JpaRepository<ListaCompra, Long> {
    List<ListaCompra> findByUsuarioId(long usuarioId);
}
