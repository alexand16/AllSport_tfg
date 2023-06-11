package modelo.entidades;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.entidades.Clientes;
import modelo.entidades.Posts;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-06-11T16:26:02")
@StaticMetamodel(Respuestas.class)
public class Respuestas_ { 

    public static volatile SingularAttribute<Respuestas, String> contenido;
    public static volatile SingularAttribute<Respuestas, Posts> post;
    public static volatile SingularAttribute<Respuestas, LocalDate> fechaCreacion;
    public static volatile SingularAttribute<Respuestas, Clientes> usuario;
    public static volatile SingularAttribute<Respuestas, Long> id;

}