package com.spring.spring.controller;


import com.spring.spring.modelo.Usuario;
import com.spring.spring.dao.UsuarioDao;
import com.spring.spring.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuariodao;

    @Autowired
    JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuarios")
    public List<Usuario> GetUsuarios(@RequestHeader(value = "autorizacion") String token) {

        if(validartoken(token)){
            return usuariodao.getUsuarios();
        }
        return new ArrayList<>();
    }

    private boolean validartoken(String token){

        String usuarioid = jwtUtil.getKey(token);
        if(usuarioid==null){
            return false;
        }
        return true;
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void DeleteUsuarios(@RequestHeader(value = "autorizacion") String token,
                               @PathVariable Long id) {

       if(validartoken(token)){
           usuariodao.eliminar(id);
       }
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void CrearUsuario(@RequestBody Usuario usuario) {
        usuariodao.crear(usuario);
    }

}
