package mx.edu.utez.u3_04.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String clave;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "^(?!.*<script).*$", message = "No se permiten etiquetas <script>")
    private String estado;

    @NotBlank(message = "El municipio es obligatorio")
    @Pattern(regexp = "^(?!.*<script).*$", message = "No se permiten etiquetas <script>")
    private String municipio;
}