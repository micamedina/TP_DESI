package tuti.desi.entidades;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name = "vuelos")
public class Vuelo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "numero_vuelo", nullable = false, unique = true)
	private String nroVuelo;
	
	@ManyToOne
    @JoinColumn(name = "origen_id", nullable = false)
    private Ciudad origen;
	
	@ManyToOne
    @JoinColumn(name = "destino_id", nullable = false)
    private Ciudad destino;
	
	@Column(name = "tipo_vuelo", nullable = false)
    private String tipoVuelo;
	
	@Column(name = "precio_pasaje", nullable = false)
	private double precioPasaje;
	
	@Column(name = "fecha_hora_partida", nullable = false)
    private LocalDateTime fechaHoraPartida;
	
	@ManyToOne
    @JoinColumn(name = "avion_id", nullable = false)
    private Avion avion;
	
	@Column(name = "estado", nullable = false)
	private String estado;
	
	@OneToMany(mappedBy = "vuelo", cascade = CascadeType.ALL)
    private List<Asiento> asientos;
	
	public List<Asiento> getAsientos() {
		return asientos;
	}

	public void setAsientos(List<Asiento> asientos) {
		this.asientos = asientos;
	}

	public Vuelo() {
        this.estado = "NORMAL"; 
    }
	
	public Ciudad getOrigen() {
		return origen;
	}
	public void setOrigen(Ciudad origen) {
		this.origen = origen;
	}
	public Ciudad getDestino() {
		return destino;
	}
	public void setDestino(Ciudad destino) {
		this.destino = destino;
	}
	public String getTipoVuelo() {
		return tipoVuelo;
	}
	public void setTipoVuelo(String tipoVuelo) {
		this.tipoVuelo = tipoVuelo;
	}
	public double getPrecioPasaje() {
		return precioPasaje;
	}
	public void setPrecioPasaje(double precioPasaje) {
		this.precioPasaje = precioPasaje;
	}
	public LocalDateTime getFechaHoraPartida() {
		return fechaHoraPartida;
	}
	public void setFechaHoraPartida(LocalDateTime fechaHoraPartida) {
		this.fechaHoraPartida = fechaHoraPartida;
	}
	public Avion getAvion() {
		return avion;
	}
	public void setAvion(Avion avion) {
		this.avion = avion;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNroVuelo() {
		return nroVuelo;
	}
	public void setNroVuelo(String nombre) {
		this.nroVuelo = nombre;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
