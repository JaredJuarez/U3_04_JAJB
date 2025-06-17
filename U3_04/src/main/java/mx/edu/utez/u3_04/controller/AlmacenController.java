package mx.edu.utez.u3_04.controller;

import jakarta.validation.Valid;
import mx.edu.utez.u3_04.model.Almacen;
import mx.edu.utez.u3_04.service.AlmacenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/almacenes")
public class AlmacenController {

    @Autowired
    private AlmacenService almacenService;

    @GetMapping
    public List<Almacen> getAll() {
        return almacenService.findAll();
    }

    @GetMapping("/{id}")
    public Almacen getById(@PathVariable Long id) {
        return almacenService.findById(id)
                .orElseThrow(() -> new RuntimeException("Almacen not found"));
    }

    @PostMapping
    public Almacen create(@Valid @RequestBody Almacen almacen) {
        return almacenService.save(almacen);
    }

    @PutMapping("/{id}")
    public Almacen update(@PathVariable Long id, @Valid @RequestBody Almacen almacen) {
        Almacen existente = almacenService.findById(id)
                .orElseThrow(() -> new RuntimeException("Almacen not found"));
        almacen.setId(id);
        // Mantener la clave original al actualizar
        almacen.setClave(existente.getClave());
        return almacenService.save(almacen);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        almacenService.deleteById(id);
    }
}