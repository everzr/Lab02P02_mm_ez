/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author EverZr
 */
@Entity
@Table(name = "vuelos")
@NamedQueries({
    @NamedQuery(name = "Vuelos.findAll", query = "SELECT v FROM Vuelos v"),
    @NamedQuery(name = "Vuelos.findByIDVuelo", query = "SELECT v FROM Vuelos v WHERE v.iDVuelo = :iDVuelo"),
    @NamedQuery(name = "Vuelos.findByNumeroVuelo", query = "SELECT v FROM Vuelos v WHERE v.numeroVuelo = :numeroVuelo"),
    @NamedQuery(name = "Vuelos.findByAerolinea", query = "SELECT v FROM Vuelos v WHERE v.aerolinea = :aerolinea"),
    @NamedQuery(name = "Vuelos.findByOrigen", query = "SELECT v FROM Vuelos v WHERE v.origen = :origen"),
    @NamedQuery(name = "Vuelos.findByDestino", query = "SELECT v FROM Vuelos v WHERE v.destino = :destino"),
    @NamedQuery(name = "Vuelos.findByFechaSalida", query = "SELECT v FROM Vuelos v WHERE v.fechaSalida = :fechaSalida"),
    @NamedQuery(name = "Vuelos.findByHoraSalida", query = "SELECT v FROM Vuelos v WHERE v.horaSalida = :horaSalida"),
    @NamedQuery(name = "Vuelos.findByFechaLlegada", query = "SELECT v FROM Vuelos v WHERE v.fechaLlegada = :fechaLlegada"),
    @NamedQuery(name = "Vuelos.findByHoraLlegada", query = "SELECT v FROM Vuelos v WHERE v.horaLlegada = :horaLlegada"),
    @NamedQuery(name = "Vuelos.findByAvion", query = "SELECT v FROM Vuelos v WHERE v.avion = :avion")})
public class Vuelos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Vuelo")
    private Integer iDVuelo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Numero_Vuelo")
    private String numeroVuelo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Aerolinea")
    private String aerolinea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Origen")
    private String origen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Destino")
    private String destino;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Salida")
    @Temporal(TemporalType.DATE)
    private Date fechaSalida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Hora_Salida")
    @Temporal(TemporalType.TIME)
    private Date horaSalida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Llegada")
    @Temporal(TemporalType.DATE)
    private Date fechaLlegada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Hora_Llegada")
    @Temporal(TemporalType.TIME)
    private Date horaLlegada;
    @Size(max = 100)
    @Column(name = "Avion")
    private String avion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDVuelo")
    private Collection<DetalleReservacion> detalleReservacionCollection;

    public Vuelos() {
    }

    public Vuelos(Integer iDVuelo) {
        this.iDVuelo = iDVuelo;
    }

    public Vuelos(Integer iDVuelo, String numeroVuelo, String aerolinea, String origen, String destino, Date fechaSalida, Date horaSalida, Date fechaLlegada, Date horaLlegada) {
        this.iDVuelo = iDVuelo;
        this.numeroVuelo = numeroVuelo;
        this.aerolinea = aerolinea;
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.horaSalida = horaSalida;
        this.fechaLlegada = fechaLlegada;
        this.horaLlegada = horaLlegada;
    }

    public Integer getIDVuelo() {
        return iDVuelo;
    }

    public void setIDVuelo(Integer iDVuelo) {
        this.iDVuelo = iDVuelo;
    }

    public String getNumeroVuelo() {
        return numeroVuelo;
    }

    public void setNumeroVuelo(String numeroVuelo) {
        this.numeroVuelo = numeroVuelo;
    }

    public String getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public Date getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(Date horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public String getAvion() {
        return avion;
    }

    public void setAvion(String avion) {
        this.avion = avion;
    }

    public Collection<DetalleReservacion> getDetalleReservacionCollection() {
        return detalleReservacionCollection;
    }

    public void setDetalleReservacionCollection(Collection<DetalleReservacion> detalleReservacionCollection) {
        this.detalleReservacionCollection = detalleReservacionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDVuelo != null ? iDVuelo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vuelos)) {
            return false;
        }
        Vuelos other = (Vuelos) object;
        if ((this.iDVuelo == null && other.iDVuelo != null) || (this.iDVuelo != null && !this.iDVuelo.equals(other.iDVuelo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Vuelos[ iDVuelo=" + iDVuelo + " ]";
    }
    
}
