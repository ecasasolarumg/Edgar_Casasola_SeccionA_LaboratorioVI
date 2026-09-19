package com.laboratorio.ejercicio6.controller;
import com.laboratorio.ejercicio6.model.Reserva;
import com.laboratorio.ejercicio6.service.ReservaService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {
    private final ReservaService service;
    public ReservaController(ReservaService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Reserva> registrar(@RequestBody Reserva item) { return new ResponseEntity<>(service.registrar(item), HttpStatus.CREATED); }
    
    @GetMapping
    public ResponseEntity<List<Reserva>> consultar() { return ResponseEntity.ok(service.consultarTodos()); }
    
    @GetMapping("/busqueda/{param}")
    public ResponseEntity<Reserva> buscar(@PathVariable String param) {
        return service.buscar(param).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Reserva> actualizar(@PathVariable Long id, @RequestBody Reserva item) {
        return service.actualizar(id, item).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return service.eliminar(id) ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}