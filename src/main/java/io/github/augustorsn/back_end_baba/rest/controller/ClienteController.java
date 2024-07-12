package io.github.augustorsn.back_end_baba.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.augustorsn.back_end_baba.domain.Cliente;
import io.github.augustorsn.back_end_baba.repository.ClientesJpa;



@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private ClientesJpa clientes;

    public ClienteController(ClientesJpa clientes) {
        this.clientes = clientes;
    }
    
    @GetMapping("{id}")    
    public Cliente getClienteById(@PathVariable Integer id) {
        Optional<Cliente> cliente = clientes.findById(id);
        if (cliente.isPresent()) {
            return cliente.get();
        }
       throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @SuppressWarnings("rawtypes")
    @PostMapping   
    public ResponseEntity save(@RequestBody Cliente cliente) {
        Cliente clienteSave = clientes.save(cliente);
        return ResponseEntity.ok(clienteSave);
    }

    @SuppressWarnings("rawtypes")
    @DeleteMapping("{id}")    
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Cliente> c = clientes.findById(id);
        if (c.isPresent()) {
            clientes.delete(c.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @SuppressWarnings("rawtypes")
    @PutMapping("{id}")    
    public ResponseEntity update(@PathVariable Integer id, @RequestBody Cliente cliente) {

        Optional<Cliente> c = clientes.findById(id);

        if (c.isPresent()) {
            cliente.setId(c.get().getId());
            clientes.save(cliente);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

  
 
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @GetMapping   
    public ResponseEntity find(Cliente filtro){
        
        ExampleMatcher matcher = ExampleMatcher.matching()
                                                .withIgnoreCase()
                                                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro,matcher);
        List<Cliente> lista = clientes.findAll(example);
        return ResponseEntity.ok(lista);        
    }

}
