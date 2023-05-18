package modelo.entidades;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.entidades.Clientes;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-05-18T17:40:40")
@StaticMetamodel(Pedidos.class)
public class Pedidos_ { 

    public static volatile SingularAttribute<Pedidos, Clientes> cliente;
    public static volatile SingularAttribute<Pedidos, Double> total;
    public static volatile SingularAttribute<Pedidos, String> estadoPedido;
    public static volatile SingularAttribute<Pedidos, String> rutaFactura;
    public static volatile SingularAttribute<Pedidos, LocalDate> fechaPedido;
    public static volatile SingularAttribute<Pedidos, Long> id;

}