package mx.edu.utez.u3_04.service;

import mx.edu.utez.u3_04.model.Almacen;
import mx.edu.utez.u3_04.model.Cede;
import mx.edu.utez.u3_04.repository.AlmacenRepository;
import mx.edu.utez.u3_04.repository.CedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlmacenService {

    @Autowired
    private AlmacenRepository almacenRepository;

    @Autowired
    private CedeRepository cedeRepository;

    public List<Almacen> findAll() {
        return almacenRepository.findAll();
    }

    public Optional<Almacen> findById(Long id) {
        return almacenRepository.findById(id);
    }

    public Almacen save(Almacen almacen) {
        boolean esNuevo = almacen.getId() == null;

        // 1. Guarda primero para generar el id si es nuevo
        Almacen guardado = almacenRepository.save(almacen);

        if (esNuevo) {
            // 2. Busca la cede para obtener la clave real
            Cede cede = cedeRepository.findById(guardado.getCede().getId())
                    .orElseThrow(() -> new RuntimeException("Cede no encontrada"));
            // 3. Genera la clave del Almacen
            String clave = cede.getClave() + "-A" + guardado.getId();
            guardado.setClave(clave);
            // 4. Guarda de nuevo solo si la clave es nueva
            guardado = almacenRepository.save(guardado);
        }
        return guardado;
    }

    public void deleteById(Long id) {
        almacenRepository.deleteById(id);
    }
}
