package com.miempresa.sprinjpa.interfaceServicio;

import java.util.List;
import java.util.Optional;

import com.miempresa.sprinjpa.modelo.Empleado;

public interface IEmpleadoServicio {
    public List<Empleado> listar();
    public List<Empleado> buscar(String nombre);
    public Optional<Empleado> listarId(int id);
    public int guardar(Empleado id);
    public void borrar(int id);
} 
