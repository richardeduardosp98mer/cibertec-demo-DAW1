package pe.cibertec.entity;




@Data
@Entity
@Table(name = "usuario")
public class Usuario {
    private long id;
    private String nombre;
    private String correo;
    private String clave;
}
