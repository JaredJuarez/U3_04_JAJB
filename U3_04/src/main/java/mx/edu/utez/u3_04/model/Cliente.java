package mx.edu.utez.u3_04.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre completo es obligatorio")
    @Pattern(regexp = "^(?!.*<script).*$", message = "No se permiten etiquetas <script>")
    private String nombreCompleto;

    @NotBlank(message = "El telefono es obligatorio")
    @Pattern(regexp = "^(?!.*<script).*$", message = "No se permiten etiquetas <script>")
    private String telefono;

    @NotBlank(message = "Todos los campos son obligatorios")
    @Pattern(regexp = "^(?!.*<script).*$", message = "No se permiten etiquetas <script>")
    private String correoElectronico;
}