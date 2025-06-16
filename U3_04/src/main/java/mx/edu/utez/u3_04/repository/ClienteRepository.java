package mx.edu.utez.u3_04.repository;

import mx.edu.utez.u3_04.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {}