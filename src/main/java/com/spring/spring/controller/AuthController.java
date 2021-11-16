package com.spring.spring.controller;

import com.spring.spring.modelo.Usuario;
import com.spring.spring.dao.UsuarioDao;
import com.spring.spring.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {


    @Autowired
    private UsuarioDao usuariodao;

    @Autowired
    private JWTUtil jwtutil ;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario) {

        Usuario logeado = usuariodao.verificar(usuario);
        if(logeado!=null){

          String token =   jwtutil.create(logeado.getId()+"",logeado.getEmail());
            return token;
        }else{
            return "no";
        }

    }
}
