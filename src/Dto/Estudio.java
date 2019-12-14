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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alexander
 */
@Entity
@Table(name = "estudio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estudio.findAll", query = "SELECT e FROM Estudio e"),
    @NamedQuery(name = "Estudio.findById", query = "SELECT e FROM Estudio e WHERE e.id = :id"),
    @NamedQuery(name = "Estudio.findByTitulo", query = "SELECT e FROM Estudio e WHERE e.titulo = :titulo"),
    @NamedQuery(name = "Estudio.findByPeriodofin", query = "SELECT e FROM Estudio e WHERE e.periodofin = :periodofin"),
    @NamedQuery(name = "Estudio.findByLugar", query = "SELECT e FROM Estudio e WHERE e.lugar = :lugar")})
public class Estudio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "periodofin")
    private String periodofin;
    @Column(name = "lugar")
    private String lugar;
    @JoinColumn(name = "egresado", referencedColumnName = "id")
    @ManyToOne
    private Egresado egresado;
    @JoinColumn(name = "nivel", referencedColumnName = "id")
    @ManyToOne
    private Nivel nivel;

    public Estudio() {}
    
    public Estudio (String titulo, String periodofin, String lugar, Nivel nivel, Egresado e) {
    	this.egresado = e;
    	this.lugar = lugar;
    	this.titulo = titulo;
    	this.nivel = nivel;
    }

    public Estudio(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPeriodofin() {
        return periodofin;
    }

    public void setPeriodofin(String periodofin) {
        this.periodofin = periodofin;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Egresado getEgresado() {
        return egresado;
    }

    public void setEgresado(Egresado egresado) {
        this.egresado = egresado;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
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
        if (!(object instanceof Estudio)) {
            return false;
        }
        Estudio other = (Estudio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Dao.Estudio[ id=" + id + " ]";
    }
    
}
