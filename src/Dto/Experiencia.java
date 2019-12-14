/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ESTUDIANTE
 */
@Entity
@Table(name = "experiencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Experiencia.findAll", query = "SELECT e FROM Experiencia e")
    , @NamedQuery(name = "Experiencia.findById", query = "SELECT e FROM Experiencia e WHERE e.id = :id")
    , @NamedQuery(name = "Experiencia.findByDescripcion", query = "SELECT e FROM Experiencia e WHERE e.descripcion = :descripcion")
    , @NamedQuery(name = "Experiencia.findByPeriodoinicio", query = "SELECT e FROM Experiencia e WHERE e.periodoinicio = :periodoinicio")
    , @NamedQuery(name = "Experiencia.findByPeriodofin", query = "SELECT e FROM Experiencia e WHERE e.periodofin = :periodofin")})
public class Experiencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "descripcion")
    private String descripcion;
    @Lob
    @Column(name = "funciones")
    private String funciones;
    @Column(name = "periodoinicio")
    private String periodoinicio;
    @Column(name = "periodofin")
    private String periodofin;
    @JoinColumn(name = "egresado", referencedColumnName = "id")
    @ManyToOne
    private Egresado egresado;

    public Experiencia() {
    }

    public Experiencia(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFunciones() {
        return funciones;
    }

    public void setFunciones(String funciones) {
        this.funciones = funciones;
    }

    public String getPeriodoinicio() {
        return periodoinicio;
    }

    public void setPeriodoinicio(String periodoinicio) {
        this.periodoinicio = periodoinicio;
    }

    public String getPeriodofin() {
        return periodofin;
    }

    public void setPeriodofin(String periodofin) {
        this.periodofin = periodofin;
    }

    public Egresado getEgresado() {
        return egresado;
    }

    public void setEgresado(Egresado egresado) {
        this.egresado = egresado;
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
        if (!(object instanceof Experiencia)) {
            return false;
        }
        Experiencia other = (Experiencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Dto.Experiencia[ id=" + id + " ]";
    }
    
}
