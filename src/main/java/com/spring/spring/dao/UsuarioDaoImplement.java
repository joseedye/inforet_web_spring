package com.spring.spring.dao;

import com.spring.spring.modelo.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UsuarioDaoImplement implements UsuarioDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario";

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
    }

    @Override
    public void crear(Usuario usuario) {
        Argon2 encriptar= Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String contraencriptada = encriptar.hash(1,1024,1, usuario.getPasword());
        usuario.setPasword(contraencriptada);
        entityManager.merge(usuario);
    }

    public Usuario verificar(Usuario usuario) {

        String query = "FROM Usuario WHERE email = :email";
        List<Usuario> lista =  entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();

        if(lista.isEmpty()){return null;}

        Argon2 encriptar= Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

         if(encriptar.verify(lista.get(0).getPasword(),usuario.getPasword())){
             return lista.get(0);
         }else {
             return null;
         }
    }
}
