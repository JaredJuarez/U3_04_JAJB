package mx.edu.utez.u3_04.controller;

import jakarta.validation.Valid;
import mx.edu.utez.u3_04.model.Cliente;
import mx.edu.utez.u3_04.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> getAll() {
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    public Cliente getById(@PathVariable Long id) {
        return clienteService.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente not found"));
    }

    @PostMapping
    public Cliente create(@Valid @RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }

    @PutMapping("/{id}")
    public Cliente update(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
        Cliente existente = clienteService.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente not found"));
        cliente.setId(id);
        // Si tienes algún campo que no debe cambiar, aquí puedes conservarlo
        return clienteService.save(cliente);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        clienteService.deleteById(id);
    }
}