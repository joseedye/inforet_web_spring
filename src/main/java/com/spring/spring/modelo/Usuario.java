package com.spring.spring.modelo;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="usuarios")
@ToString @EqualsAndHashCode
public class Usuario {

    @Id
    @Getter @Setter @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Getter @Setter @Column(name ="nombre")
    private String nombre;
    @Getter @Setter @Column(name ="apellido")
    private String apellido;
    @Getter @Setter @Column(name ="email")
    private String email;
    @Getter @Setter @Column(name ="telefono")
    private String telefono;
    @Getter @Setter @Column(name ="password")
    private String pasword;


}
