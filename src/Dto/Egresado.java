/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ESTUDIANTE
 */
@Entity
@Table(name = "egresado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Egresado.findAll", query = "SELECT e FROM Egresado e")
    , @NamedQuery(name = "Egresado.findById", query = "SELECT e FROM Egresado e WHERE e.id = :id")
    , @NamedQuery(name = "Egresado.findByDocumento", query = "SELECT e FROM Egresado e WHERE e.documento = :documento")
    , @NamedQuery(name = "Egresado.findByNombre", query = "SELECT e FROM Egresado e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Egresado.findByTelefono", query = "SELECT e FROM Egresado e WHERE e.telefono = :telefono")
    , @NamedQuery(name = "Egresado.findByEmail", query = "SELECT e FROM Egresado e WHERE e.email = :email")
    , @NamedQuery(name = "Egresado.findByCodigo", query = "SELECT e FROM Egresado e WHERE e.codigo = :codigo")
    , @NamedQuery(name = "Egresado.findByClave", query = "SELECT e FROM Egresado e WHERE e.clave = :clave")})
public class Egresado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "documento")
    private String documento;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "email")
    private String email;
    @Column(name = "codigo")
    private String codigo;
    @Lob
    @Column(name = "perfil")
    private String perfil;
    @Column(name = "clave")
    private String clave;
    @OneToMany(mappedBy = "egresado")
    private List<Estudio> estudioList;
    @OneToMany(mappedBy = "egresado")
    private List<Experiencia> experienciaList;
    @JoinColumn(name = "programa", referencedColumnName = "codigo")
    @ManyToOne
    private Programa programa;

    public Egresado() {
    }

    public Egresado(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @XmlTransient
    public List<Estudio> getEstudioList() {
        return estudioList;
    }

    public void setEstudioList(List<Estudio> estudioList) {
        this.estudioList = estudioList;
    }

    @XmlTransient
    public List<Experiencia> getExperienciaList() {
        return experienciaList;
    }

    public void setExperienciaList(List<Experiencia> experienciaList) {
        this.experienciaList = experienciaList;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
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
        if (!(object instanceof Egresado)) {
            return false;
        }
        Egresado other = (Egresado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Dto.Egresado[ id=" + id + " ]";
    }
    
}