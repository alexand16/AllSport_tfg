package modelo.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.entidades.Actividades;
import modelo.entidades.Cuotas;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-06-11T16:26:02")
@StaticMetamodel(ActividadesPorCuota.class)
public class ActividadesPorCuota_ { 

    public static volatile SingularAttribute<ActividadesPorCuota, Cuotas> cuota;
    public static volatile SingularAttribute<ActividadesPorCuota, Long> id;
    public static volatile SingularAttribute<ActividadesPorCuota, Actividades> actividad;

}