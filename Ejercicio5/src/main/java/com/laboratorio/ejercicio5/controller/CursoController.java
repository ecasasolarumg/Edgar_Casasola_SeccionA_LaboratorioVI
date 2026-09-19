package com.laboratorio.ejercicio5.controller;
import com.laboratorio.ejercicio5.model.Curso;
import com.laboratorio.ejercicio5.service.CursoService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {
    private final CursoService service;
    public CursoController(CursoService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Curso> registrar(@RequestBody Curso item) { return new ResponseEntity<>(service.registrar(item), HttpStatus.CREATED); }
    
    @GetMapping
    public ResponseEntity<List<Curso>> consultar() { return ResponseEntity.ok(service.consultarTodos()); }
    
    @GetMapping("/busqueda/{param}")
    public ResponseEntity<Curso> buscar(@PathVariable String param) {
        return service.buscar(param).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizar(@PathVariable Long id, @RequestBody Curso item) {
        return service.actualizar(id, item).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return service.eliminar(id) ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}