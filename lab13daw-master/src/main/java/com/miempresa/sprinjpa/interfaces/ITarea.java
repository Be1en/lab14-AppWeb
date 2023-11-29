package com.miempresa.sprinjpa.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.miempresa.sprinjpa.modelo.Tarea;

@Repository
public interface ITarea extends CrudRepository<Tarea, Integer>{
    @Query(value = "select * from tarea where descripcion like %:descripcion%", nativeQuery = true)
    public List<Tarea> findTarea(@Param("descripcion") String descripcion);
}
