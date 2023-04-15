/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author alanr
 */
@Entity
@Table(name = "clientes")
public class Clientes implements Serializable {

    //properties
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
        @Column(name = "tipo_usuario")
    private String tipoUsuario;
    
    @Column(name = "USUARIO")
    private String usuario;
    
    @Column(name = "CONTRASEÑA")
    private String contrasena;
    
    @Column(name = "fecha_alta")
    private LocalDate fechaAlta;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "apellidos")
    private String apellidos;
    
    @Column(name = "telefono")
    private String telefono;
    
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "fecha_pago")
    private LocalDate fechaPago;
    
    @Column(name = "estado_membresia")
    private String estadoMembresia;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_cuota_id")
    private Cuotas cuota;
    
    @Column(name = "puntos")
    private int puntos;
    
    @Column(name = "ruta_img")
    private String rutaImg;
    
    @Column(name = "observaciones")
    private String observaciones;

    //getters & setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clientes)) {
            return false;
        }
        Clientes other = (Clientes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.entidades.Clientes[ id=" + id + " ]";
    }
    
}
