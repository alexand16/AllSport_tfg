package modelo.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.entidades.Clientes;
import modelo.entidades.Ejercicios;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-06-11T16:26:02")
@StaticMetamodel(Lista_Ejercicios.class)
public class Lista_Ejercicios_ { 

    public static volatile SingularAttribute<Lista_Ejercicios, Integer> series;
    public static volatile SingularAttribute<Lista_Ejercicios, Integer> repeticiones;
    public static volatile SingularAttribute<Lista_Ejercicios, Clientes> usuario;
    public static volatile SingularAttribute<Lista_Ejercicios, Long> id;
    public static volatile SingularAttribute<Lista_Ejercicios, Ejercicios> ejercicio;

}