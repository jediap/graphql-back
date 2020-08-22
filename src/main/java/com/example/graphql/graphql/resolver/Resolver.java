package com.example.graphql.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.graphql.entity.User;
import org.springframework.stereotype.Component;


/**
 * MUCHA DUDA PARA QUE SIRVE ESTA CLASE. INVESTIGAR BIEN
 * Deduciendo viendo ejemplos simples: Estas clases se usan para obtener cuando hay foreign keys. Por ejemplo:
 * Quiero saber la dirección (tabla direccion) del usuario (tabla usuario)
 * IMPORTANTE -> Habria que investigar bien como hacer consultas con joins y esas cosas.
 * Por tanto, alomejor esta clase no es necesaria.
 */
@Component
public class Resolver implements GraphQLResolver<User> {

    // Address getAddress(User user);
}
