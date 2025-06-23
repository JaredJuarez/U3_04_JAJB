package mx.edu.utez.u3_04.controller;

import jakarta.validation.Valid;
import mx.edu.utez.u3_04.model.Transaccion;
import mx.edu.utez.u3_04.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @GetMapping
    public List<Transaccion> getAll() {
        return transaccionService.findAll();
    }

    @GetMapping("/{id}")
    public Transaccion getById(@PathVariable Long id) {
        return transaccionService.findById(id)
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada"));
    }

    @PostMapping
    public Transaccion create(@Valid @RequestBody Transaccion transaccion) {
        return transaccionService.save(transaccion);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        transaccionService.deleteById(id);
    }

    @GetMapping("/almacen/{almacenId}")
    public List<Transaccion> getByAlmacen(@PathVariable Long almacenId) {
        return transaccionService.findByAlmacen(almacenId);
    }
}