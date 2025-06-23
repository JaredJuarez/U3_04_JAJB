package mx.edu.utez.u3_04.repository;

import mx.edu.utez.u3_04.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
    List<Transaccion> findByAlmacenId(Long almacenId);
    boolean existsByAlmacenIdAndTipo(Long almacenId, String tipo);
}