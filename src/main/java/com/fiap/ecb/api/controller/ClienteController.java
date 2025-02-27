package com.fiap.ecb.api.controller;

import com.fiap.ecb.api.model.Cliente;
import com.fiap.ecb.api.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public List<Cliente> listarClientes() {
        return service.listarClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
        Optional<Cliente> cliente = service.buscarClientePorId(id);
        return cliente.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente criarCliente(@RequestBody Cliente cliente) {
        return service.salvarCliente(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteAtualizado) {
        if (!service.existeCliente(id)) {
            return ResponseEntity.notFound().build();
        }
        clienteAtualizado.setId(id);
        return ResponseEntity.ok(service.salvarCliente(clienteAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerCliente(@PathVariable Long id) {
        if (!service.existeCliente(id)) {
            return ResponseEntity.notFound().build();
        }
        service.excluirCliente(id);
        return ResponseEntity.noContent().build();
    }
}