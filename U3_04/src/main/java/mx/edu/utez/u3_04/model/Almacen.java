package mx.edu.utez.u3_04.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Almacen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clave;
    private double precioVenta;
    private double precioRenta;
    private String tamano;

    private String estado; // "LIBRE", "RENTADO", "VENDIDO"

    @ManyToOne
    @JoinColumn(name = "cede_id")
    private Cede cede;
}