package com.laboratorio.ejercicio5.service;
import com.laboratorio.ejercicio5.model.Curso;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CursoService {
    private List<Curso> lista = new ArrayList<>();
    private Long idCounter = 1L;

    public Curso registrar(Curso item) {
        item.setId(idCounter++);
        lista.add(item);
        return item;
    }
    public List<Curso> consultarTodos() { return lista; }
    public Optional<Curso> buscar(String parametro) {
        return lista.stream().filter(i -> String.valueOf(i.getCodigo()).equalsIgnoreCase(parametro)).findFirst();
    }
    public Optional<Curso> actualizar(Long id, Curso actualizado) {
        return lista.stream().filter(i -> i.getId().equals(id)).findFirst().map(item -> {
                    item.setNombre(actualizado.getNombre());
                    item.setCodigo(actualizado.getCodigo());
                    item.setCreditos(actualizado.getCreditos());
                    item.setEstado(actualizado.getEstado());
                    return item;
                });
    }
    public boolean eliminar(Long id) { return lista.removeIf(i -> i.getId().equals(id)); }
}