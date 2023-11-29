package com.miempresa.sprinjpa.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miempresa.sprinjpa.interfaceServicio.ITareaServicio;
import com.miempresa.sprinjpa.interfaces.ITarea;
import com.miempresa.sprinjpa.modelo.Tarea;

@Service
public class TareaServicio implements ITareaServicio{
    
    @Autowired
    private ITarea repo;

    @Override
    public List<Tarea> listar() {
        return (List<Tarea>)repo.findAll();
    }

    @Override
    public Optional<Tarea> listarId(int id) {
        return repo.findById(id);
    }

    @Override
    public int guardar(Tarea id) {
        Tarea ta = repo.save(id);
        if(!ta.equals(null)){
            return 1;
        }
        return 0;
    }

    @Override
    public void borrar(int id) {
        repo.deleteById(id);
        
    }
    @Override
    public List<Tarea> buscar(String descripcion) {
        return (List<Tarea>)repo.findTarea(descripcion);
    }

}
