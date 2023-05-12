package model;

public class Ingrediente {

	String nombreIngrediente;
	String cantidad;
	String medida;
	
	public Ingrediente(String nombreIngrediente, String cantidad, String medida) {
		super();
		this.nombreIngrediente = nombreIngrediente;
		this.cantidad = cantidad;
		this.medida = medida;
	}
	
	public Ingrediente() {
		super();
	}

	public String getNombreIngrediente() {
		return nombreIngrediente;
	}
	public void setNombreIngrediente(String nombreIngrediente) {
		this.nombreIngrediente = nombreIngrediente;
	}
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	public String getMedida() {
		return medida;
	}
	public void setMedida(String medida) {
		this.medida = medida;
	}

	@Override
	public String toString() {
		return "Ingrediente [nombreIngrediente=" + nombreIngrediente + ", cantidad=" + cantidad + ", medida=" + medida
				+ "]";
	}
	
	
	
}
