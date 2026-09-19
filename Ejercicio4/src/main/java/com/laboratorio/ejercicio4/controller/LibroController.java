package com.laboratorio.ejercicio4.controller;
import com.laboratorio.ejercicio4.model.Libro;
import com.laboratorio.ejercicio4.service.LibroService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/libros")
public class LibroController {
    private final LibroService service;
    public LibroController(LibroService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Libro> registrar(@RequestBody Libro item) { return new ResponseEntity<>(service.registrar(item), HttpStatus.CREATED); }
    
    @GetMapping
    public ResponseEntity<List<Libro>> consultar() { return ResponseEntity.ok(service.consultarTodos()); }
    
    @GetMapping("/busqueda/{param}")
    public ResponseEntity<Libro> buscar(@PathVariable String param) {
        return service.buscar(param).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizar(@PathVariable Long id, @RequestBody Libro item) {
        return service.actualizar(id, item).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return service.eliminar(id) ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}