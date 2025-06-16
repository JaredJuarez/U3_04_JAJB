package mx.edu.utez.u3_04.controller;

import mx.edu.utez.u3_04.model.Cede;
import mx.edu.utez.u3_04.service.CedeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/cedes")
public class CedeController {
    @Autowired
    private CedeService cedeService;

    @GetMapping
    public List<Cede> listar() {
        return cedeService.findAll();
    }

    @PostMapping
    public Cede crear(@Valid @RequestBody Cede cede) {
        // 1. Guardar la entidad para que se genere el id
        Cede guardada = cedeService.save(cede);

        // 2. Generar la clave usando el id
        String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        int random = new Random().nextInt(9000) + 1000;
        String clave = "C" + guardada.getId() + "-" + fecha + "-" + random;
        guardada.setClave(clave);

        // 3. Guardar de nuevo para actualizar la clave
        guardada = cedeService.save(guardada);

        return guardada;
    }

    @GetMapping("/{id}")
    public Cede obtener(@PathVariable Long id) {
        return cedeService.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Cede actualizar(@PathVariable Long id, @Valid @RequestBody Cede cede) {
        // 1. Recupera la Cede existente
        Cede existente = cedeService.findById(id)
                .orElseThrow(() -> new RuntimeException("Cede not found"));

        // 2. Mantén la clave original
        cede.setClave(existente.getClave());

        // 3. Asegura que el id es correcto
        cede.setId(id);

        // 4. Guarda la actualización
        return cedeService.save(cede);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        cedeService.delete(id);
    }
}