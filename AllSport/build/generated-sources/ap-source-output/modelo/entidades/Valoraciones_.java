package modelo.entidades;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.entidades.Clientes;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-06-01T23:46:01")
@StaticMetamodel(Valoraciones.class)
public class Valoraciones_ { 

    public static volatile SingularAttribute<Valoraciones, Clientes> cliente;
    public static volatile SingularAttribute<Valoraciones, Integer> puntuacion;
    public static volatile SingularAttribute<Valoraciones, LocalDate> fechaValoracion;
    public static volatile SingularAttribute<Valoraciones, Long> id;
    public static volatile SingularAttribute<Valoraciones, String> comentario;

}