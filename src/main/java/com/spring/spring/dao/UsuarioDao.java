package com.spring.spring.dao;

import com.spring.spring.modelo.Usuario;

import java.util.List;


public interface UsuarioDao {
    
     List<Usuario> getUsuarios();


     void eliminar(Long id);

     void crear(Usuario usuario);

     Usuario verificar (Usuario usuario);
}
