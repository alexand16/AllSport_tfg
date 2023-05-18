package modelo.entidades;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.entidades.Clientes;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-05-18T17:40:40")
@StaticMetamodel(Posts.class)
public class Posts_ { 

    public static volatile SingularAttribute<Posts, String> contenido;
    public static volatile SingularAttribute<Posts, Clientes> administrador;
    public static volatile SingularAttribute<Posts, String> titulo;
    public static volatile SingularAttribute<Posts, LocalDate> fechaCreacion;
    public static volatile SingularAttribute<Posts, Long> id;

}