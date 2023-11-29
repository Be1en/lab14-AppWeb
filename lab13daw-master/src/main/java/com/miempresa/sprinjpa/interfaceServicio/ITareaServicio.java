package com.miempresa.sprinjpa.interfaceServicio;

import java.util.List;
import java.util.Optional;

import com.miempresa.sprinjpa.modelo.Tarea;

public interface ITareaServicio {
    public List<Tarea> listar();
    public Optional<Tarea> listarId(int id);
    public int guardar(Tarea id);
    public void borrar(int id);
    public List<Tarea> buscar(String descripcion);
}
