/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import java.io.Serializable;
import java.util.Collection;
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
 * @author Alexander
 */
@Entity
@Table(name = "egresado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Egresado.findAll", query = "SELECT e FROM Egresado e"),
    @NamedQuery(name = "Egresado.findById", query = "SELECT e FROM Egresado e WHERE e.id = :id"),
    @NamedQuery(name = "Egresado.findByDocumento", query = "SELECT e FROM Egresado e WHERE e.documento = :documento"),
    @NamedQuery(name = "Egresado.findByNombre", query = "SELECT e FROM Egresado e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Egresado.findByTelefono", query = "SELECT e FROM Egresado e WHERE e.telefono = :telefono"),
    @NamedQuery(name = "Egresado.findByEmail", query = "SELECT e FROM Egresado e WHERE e.email = :email"),
    @NamedQuery(name = "Egresado.findByCodigo", query = "SELECT e FROM Egresado e WHERE e.codigo = :codigo"),
    @NamedQuery(name = "Egresado.findByClave", query = "SELECT e FROM Egresado e WHERE e.clave = :clave"),
    @NamedQuery(name = "Egresado.findByActivo", query = "SELECT e FROM Egresado e WHERE e.activo = :activo")})
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
    @Basic(optional = false)
    @Column(name = "activo")
    private int activo;
    @OneToMany(mappedBy = "egresado")
    private Collection<Estudio> estudioCollection;
    @OneToMany(mappedBy = "egresado")
    private Collection<Experiencia> experienciaCollection;
    @JoinColumn(name = "programa", referencedColumnName = "codigo")
    @ManyToOne
    private Programa programa;

    public Egresado() {
    }

    public Egresado(Integer id) {
        this.id = id;
    }

    public Egresado(Integer id, int activo) {
        this.id = id;
        this.activo = activo;
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

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    @XmlTransient
    public Collection<Estudio> getEstudioCollection() {
        return estudioCollection;
    }

    public void setEstudioCollection(Collection<Estudio> estudioCollection) {
        this.estudioCollection = estudioCollection;
    }

    @XmlTransient
    public Collection<Experiencia> getExperienciaCollection() {
        return experienciaCollection;
    }

    public void setExperienciaCollection(Collection<Experiencia> experienciaCollection) {
        this.experienciaCollection = experienciaCollection;
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
        return "Dao.Egresado[ id=" + id + " ]";
    }
    
}
