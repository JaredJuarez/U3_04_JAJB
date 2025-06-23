package mx.edu.utez.u3_04.repository;

import mx.edu.utez.u3_04.model.Almacen;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlmacenRepository extends JpaRepository<Almacen, Long> {
    List<Almacen> findByEstado(String estado);
}