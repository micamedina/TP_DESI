package tuti.desi.presentacion.cuidades;

public class CiudadesBuscarForm {
	private String nombre;
	private Long provinciaSeleccionada;
	
		
	
	public Long getProvinciaSeleccionada() {
		return provinciaSeleccionada;
	}
	public void setProvinciaSeleccionada(Long provinciaSeleccionada) {
		this.provinciaSeleccionada = provinciaSeleccionada;
	}
	
	public String getNombre() {
		if(nombre!=null && nombre.length()>0)
			return nombre;
		else
			return null;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

}
