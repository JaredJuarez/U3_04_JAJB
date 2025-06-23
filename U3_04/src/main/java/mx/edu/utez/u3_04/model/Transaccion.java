package mx.edu.utez.u3_04.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "almacen_id")
    private Almacen almacen;

    @Column(nullable = false)
    private String tipo; // "COMPRA" o "RENTA"

    @Column(nullable = false)
    private double precio;

    private LocalDate fecha; // Fecha de la transacción

    private LocalDate fechaInicio; // Solo para renta
    private LocalDate fechaFin;    // Solo para renta
}