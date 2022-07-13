package laboratorio3.models.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;



import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;



@Entity
@Table(name="bibliotecas")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="ingrese el nombre")
	private String nombre;
	
	@NotBlank(message="ingrese el nombre del libro")
	private String libro;
	
	@NotBlank
	@Max(2)
	private String cantidad;
	
	@NotNull
	@Past
	@Column(name = "fecha_nacimiento")
	@DateTimeFormat(iso = ISO.DATE)
	
    private LocalDate fechaNacimiento;
	
    private LocalDateTime fechaRegistro;
	
	
    
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getLibro() {
		return libro;
	}



	public void setLibro(String libro) {
		this.libro = libro;
	}



	public String getCantidad() {
		return cantidad;
	}



	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}



	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}



	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}



	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}



	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@PrePersist
	public void asignarFechaRegistro() {
		fechaRegistro = LocalDateTime.now();
	}
	

	
}
