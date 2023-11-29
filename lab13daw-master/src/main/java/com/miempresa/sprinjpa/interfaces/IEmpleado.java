package com.miempresa.sprinjpa.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.miempresa.sprinjpa.modelo.Empleado;

@Repository
public interface IEmpleado extends CrudRepository<Empleado, Integer>{
    @Query(value = "select * from empleado where nombre like %:nombre%", nativeQuery = true)
    public List<Empleado> findEmpleado(@Param("nombre") String nombre);
}
