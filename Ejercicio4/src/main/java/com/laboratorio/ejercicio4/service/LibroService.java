package com.laboratorio.ejercicio4.service;
import com.laboratorio.ejercicio4.model.Libro;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class LibroService {
    private List<Libro> lista = new ArrayList<>();
    private Long idCounter = 1L;

    public Libro registrar(Libro item) {
        item.setId(idCounter++);
        lista.add(item);
        return item;
    }
    public List<Libro> consultarTodos() { return lista; }
    public Optional<Libro> buscar(String parametro) {
        return lista.stream().filter(i -> String.valueOf(i.getTitulo()).equalsIgnoreCase(parametro)).findFirst();
    }
    public Optional<Libro> actualizar(Long id, Libro actualizado) {
        return lista.stream().filter(i -> i.getId().equals(id)).findFirst().map(item -> {
                    item.setTitulo(actualizado.getTitulo());
                    item.setAutor(actualizado.getAutor());
                    item.setIsbn(actualizado.getIsbn());
                    item.setAnioPublicacion(actualizado.getAnioPublicacion());
                    item.setEstado(actualizado.getEstado());
                    return item;
                });
    }
    public boolean eliminar(Long id) { return lista.removeIf(i -> i.getId().equals(id)); }
}