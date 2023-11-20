package tuti.desi.entidades;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;

@Entity
public class Persona {
	@Id
	private Long dni;
	
	private String nombre;
	
	private String apellido;
	
	private Date fechaNacimiento;
	
	/**
	 * propiedad utilizada por la capa de presentacion para saber si es una entidad ya persistida (la estoy actualizando) o es nueva, ya que si id es ingresado por el usuario, no puede ser usado como criterio para saber si fue persistida o no.
	 */
	@Transient
	private Boolean editando=false;
	
	@ManyToOne
	private Ciudad ciudad;
	
	public Long getDni() {
		return dni;
	}
	public void setDni(Long dni) {
		this.dni = dni;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Ciudad getCiudad() {
		return ciudad;
	}
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Boolean getEditando() {
		return editando;
	}
	public void setEditando(Boolean editando) {
		this.editando = editando;
	}
	
}
