package modelo.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.entidades.Pedidos;
import modelo.entidades.Productos;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-06-11T16:26:02")
@StaticMetamodel(Productos_Pedidos.class)
public class Productos_Pedidos_ { 

    public static volatile SingularAttribute<Productos_Pedidos, Pedidos> pedido;
    public static volatile SingularAttribute<Productos_Pedidos, Long> id;
    public static volatile SingularAttribute<Productos_Pedidos, Productos> producto;
    public static volatile SingularAttribute<Productos_Pedidos, Integer> cantidad;

}