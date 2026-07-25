package pe.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.cibertec.entity.ItemLista;

public interface ItemRepository extends JpaRepository<ItemLista, Long> {

}
