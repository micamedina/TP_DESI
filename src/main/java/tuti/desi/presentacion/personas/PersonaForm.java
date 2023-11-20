package tuti.desi.presentacion.personas;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import tuti.desi.entidades.Persona;

/**
 * Objeto necesario para insertar o eliminar una persona. 
 * Nótese que en lugar de referenciar al objeto Ciudad, reemplaza ese atributo por el idCiudad, lo cual resulta mas sencillo de representar en JSON
 *
 */
public class PersonaForm {

	@NotNull(message = "el dni no puede ser nulo")
	@Min(value = 7000000, message = "el dni debe ser mayor a 7000000")
	private Long dni;
	@NotNull
	@Size(min=2, max=30, message = "el valor debe estar entre 2 y 30")
	private String apellido;
	@NotNull
	@Size(min=2, max=30)
	private String nombre;
	@NotNull
	private Long idCiudad;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fechaNacimiento;
	private Boolean editando=false;
	
	public PersonaForm() {
		super();
	}
	public PersonaForm(Persona p) {
		super();
		this.nombre=p.getNombre();
		this.apellido=p.getApellido();
		this.dni=p.getDni();
		this.idCiudad=p.getCiudad().getId();
		this.fechaNacimiento=p.getFechaNacimiento();
		this.editando=true; //siempre que entre acá es porque estoy en modo edición
	}
	public Long getDni() {
		return dni;
	}
	public void setDni(Long dni) {
		this.dni = dni;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Long getIdCiudad() {
		return idCiudad;
	}
	public void setIdCiudad(Long idCiudad) {
		this.idCiudad = idCiudad;
	}
	
	public Persona toPojo()
	{
		Persona p = new Persona();
		if(!this.editando)
		{
			p.setDni(this.getDni());
		}
		p.setApellido(this.getApellido());
		p.setNombre(this.getNombre());
		p.setDni(this.getDni());
		p.setFechaNacimiento(this.getFechaNacimiento());
		p.setEditando(this.getEditando());
		
		return p;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Boolean getEditando() {
		return editando;
	}
	public void setEditando(Boolean editando) {
		this.editando = editando;
	}
	
}
