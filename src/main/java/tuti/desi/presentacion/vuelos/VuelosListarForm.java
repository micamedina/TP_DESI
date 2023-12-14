package tuti.desi.presentacion.vuelos;

import java.util.Date;

public class VuelosListarForm {
	private String fecha;
	private Long ciudadOrigen;
	private Long ciudadDestino;
	private String tipoVuelo;
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Long getCiudadOrigen() {
		return ciudadOrigen;
	}
	public void setCiudadOrigen(Long ciudadOrigen) {
		this.ciudadOrigen = ciudadOrigen;
	}
	public Long getCiudadDestino() {
		return ciudadDestino;
	}
	public void setCiudadDestino(Long ciudadDestino) {
		this.ciudadDestino = ciudadDestino;
	}
	public String getTipoVuelo() {
		if(tipoVuelo!=null && tipoVuelo.length()>0)
			return tipoVuelo;
		else
			return null;
	}
	public void setTipoVuelo(String tipoVuelo) {
		this.tipoVuelo = tipoVuelo;
	}
	
	

}
