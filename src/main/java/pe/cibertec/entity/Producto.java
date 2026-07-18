package pe.cibertec.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private Double precio;

}
