package pe.cibertec.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "listas_compra")
public class ListaCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    @ManyToOne
    private  Usuario usuario;
    @OneToMany(mappedBy = "lista", cascade = CascadeType.ALL)
    private List<ItemLista> items;
}
