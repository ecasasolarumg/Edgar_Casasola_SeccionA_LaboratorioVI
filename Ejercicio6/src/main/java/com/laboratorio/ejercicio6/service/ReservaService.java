package com.laboratorio.ejercicio6.service;
import com.laboratorio.ejercicio6.model.Reserva;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ReservaService {
    private List<Reserva> lista = new ArrayList<>();
    private Long idCounter = 1L;

    public Reserva registrar(Reserva item) {
        item.setId(idCounter++);
        lista.add(item);
        return item;
    }
    public List<Reserva> consultarTodos() { return lista; }
    public Optional<Reserva> buscar(String parametro) {
        return lista.stream().filter(i -> String.valueOf(i.getId()).equalsIgnoreCase(parametro)).findFirst();
    }
    public Optional<Reserva> actualizar(Long id, Reserva actualizado) {
        return lista.stream().filter(i -> i.getId().equals(id)).findFirst().map(item -> {
                    item.setNombreCliente(actualizado.getNombreCliente());
                    item.setHabitacion(actualizado.getHabitacion());
                    item.setFechaEntrada(actualizado.getFechaEntrada());
                    item.setFechaSalida(actualizado.getFechaSalida());
                    item.setEstado(actualizado.getEstado());
                    return item;
                });
    }
    public boolean eliminar(Long id) { return lista.removeIf(i -> i.getId().equals(id)); }
}