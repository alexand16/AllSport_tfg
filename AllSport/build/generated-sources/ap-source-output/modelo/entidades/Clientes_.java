package modelo.entidades;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.entidades.Cuotas;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-06-11T16:26:02")
@StaticMetamodel(Clientes.class)
public class Clientes_ { 

    public static volatile SingularAttribute<Clientes, String> apellidos;
    public static volatile SingularAttribute<Clientes, Cuotas> cuota;
    public static volatile SingularAttribute<Clientes, LocalDate> fechaAlta;
    public static volatile SingularAttribute<Clientes, LocalDate> fechaNacimiento;
    public static volatile SingularAttribute<Clientes, Integer> puntos;
    public static volatile SingularAttribute<Clientes, String> nombre;
    public static volatile SingularAttribute<Clientes, LocalDate> fechaPago;
    public static volatile SingularAttribute<Clientes, String> rutaImg;
    public static volatile SingularAttribute<Clientes, String> estadoMembresia;
    public static volatile SingularAttribute<Clientes, String> observaciones;
    public static volatile SingularAttribute<Clientes, String> tipoUsuario;
    public static volatile SingularAttribute<Clientes, String> usuario;
    public static volatile SingularAttribute<Clientes, String> contrasena;
    public static volatile SingularAttribute<Clientes, Long> id;
    public static volatile SingularAttribute<Clientes, String> telefono;
    public static volatile SingularAttribute<Clientes, String> email;

}