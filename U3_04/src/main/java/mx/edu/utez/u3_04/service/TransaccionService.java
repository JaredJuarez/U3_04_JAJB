package mx.edu.utez.u3_04.service;

import mx.edu.utez.u3_04.model.Almacen;
import mx.edu.utez.u3_04.model.Transaccion;
import mx.edu.utez.u3_04.repository.AlmacenRepository;
import mx.edu.utez.u3_04.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private AlmacenRepository almacenRepository;

    public List<Transaccion> findAll() {
        return transaccionRepository.findAll();
    }

    public Optional<Transaccion> findById(Long id) {
        return transaccionRepository.findById(id);
    }

    public Transaccion save(Transaccion transaccion) {
        Long almacenId = transaccion.getAlmacen().getId();
        Almacen almacen = almacenRepository.findById(almacenId)
                .orElseThrow(() -> new RuntimeException("Almacén no encontrado"));

        // Evita compra/renta múltiple si ya está vendido
        boolean comprado = transaccionRepository.existsByAlmacenIdAndTipo(almacenId, "COMPRA");
        if (comprado) {
            throw new RuntimeException("El almacén ya fue comprado y no puede ser comprado ni rentado.");
        }

        if ("RENTA".equalsIgnoreCase(transaccion.getTipo())) {
            if (!"LIBRE".equalsIgnoreCase(almacen.getEstado())) {
                throw new RuntimeException("El almacén no está disponible para renta.");
            }
            transaccion.setFecha(LocalDate.now());
            transaccion.setFechaInicio(LocalDate.now());
            transaccion.setFechaFin(LocalDate.now().plusDays(30));
            almacen.setEstado("RENTADO");
            almacenRepository.save(almacen);
        } else if ("COMPRA".equalsIgnoreCase(transaccion.getTipo())) {
            if (!"LIBRE".equalsIgnoreCase(almacen.getEstado())) {
                throw new RuntimeException("El almacén no está disponible para compra.");
            }
            transaccion.setFecha(LocalDate.now());
            almacen.setEstado("VENDIDO");
            almacenRepository.save(almacen);
        } else {
            throw new RuntimeException("Tipo de transacción inválido: debe ser COMPRA o RENTA.");
        }

        return transaccionRepository.save(transaccion);
    }

    public void deleteById(Long id) {
        Optional<Transaccion> optTransaccion = transaccionRepository.findById(id);
        if (optTransaccion.isPresent()) {
            Transaccion transaccion = optTransaccion.get();
            Almacen almacen = transaccion.getAlmacen();
            if (almacen != null) {
                almacen.setEstado("LIBRE");
                almacenRepository.save(almacen);
            }
            transaccionRepository.deleteById(id);
        } else {
            throw new RuntimeException("Transacción no encontrada");
        }
    }

    public List<Transaccion> findByAlmacen(Long almacenId) {
        return transaccionRepository.findByAlmacenId(almacenId);
    }

    // Este método debe llamarse periódicamente (por ejemplo, con un scheduler)
    // para liberar almacenes cuya renta ya expiró
    public void liberarAlmacenesRentados() {
        List<Almacen> rentados = almacenRepository.findByEstado("RENTADO");
        LocalDate hoy = LocalDate.now();
        for (Almacen almacen : rentados) {
            // Busca la última renta del almacén
            List<Transaccion> rentas = transaccionRepository.findByAlmacenId(almacen.getId());
            for (Transaccion t : rentas) {
                if ("RENTA".equalsIgnoreCase(t.getTipo()) && t.getFechaFin() != null && !t.getFechaFin().isAfter(hoy)) {
                    almacen.setEstado("LIBRE");
                    almacenRepository.save(almacen);
                    break;
                }
            }
        }
    }
}