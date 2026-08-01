package pe.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.cibertec.entity.ItemLista;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemLista, Long> {
    @Query("SELECT i FROM ItemLista  i WHERE i.lista.id = :idLista")
    List<ItemLista> detalleLista(@Param("idLista") Long idLista);
}
