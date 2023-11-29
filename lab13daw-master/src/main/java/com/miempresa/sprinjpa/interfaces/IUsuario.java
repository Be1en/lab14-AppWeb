package com.miempresa.sprinjpa.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.miempresa.sprinjpa.modelo.Usuario;

@Repository
public interface IUsuario extends JpaRepository<Usuario,Integer>{
    Usuario findByNombre(String nombre);
}
