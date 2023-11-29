package com.miempresa.sprinjpa.servicio;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miempresa.sprinjpa.interfaceServicio.*;
import com.miempresa.sprinjpa.interfaces.IEmpleado;
import com.miempresa.sprinjpa.modelo.Empleado;

@Service
public class EmpleadoServicio implements IEmpleadoServicio{
	
    @Autowired
    private IEmpleado repo;

	@Override
	public List<Empleado> listar() {
		return (List<Empleado>)repo.findAll();
	}

	@Override
	public Optional<Empleado> listarId(int id) {
        return repo.findById(id);
	}

	@Override
	public int guardar(Empleado p) {
		Empleado em = repo.save(p);
        if(!em.equals(null)){
            return 1;
        }
        return 0;
	}

	@Override
	public void borrar(int id) {
		repo.deleteById(id);
	}

	@Override
	public List<Empleado> buscar(String nombre) {
		return (List<Empleado>)repo.findEmpleado(nombre);
	}
	
}
