package tuti.desi.entidades;

import jakarta.persistence.*;

@Entity
public class Tasa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private double iva;
	private double tasaNacional;
	private double tasaInternacional;
	private double cotizacionDolar;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public double getTasaNacional() {
		return tasaNacional;
	}

	public void setTasaNacional(double tasaNacional) {
		this.tasaNacional = tasaNacional;
	}

	public double getTasaInternacional() {
		return tasaInternacional;
	}

	public void setTasaInternacional(double tasaInternacional) {
		this.tasaInternacional = tasaInternacional;
	}

	public double getCotizacionDolar() {
		return cotizacionDolar;
	}

	public void setCotizacionDolar(double cotizacionDolar) {
		this.cotizacionDolar = cotizacionDolar;
	}
}
