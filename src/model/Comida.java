package model;

import java.util.ArrayList;
import java.util.List;

public class Comida extends Receta implements IReceta{

	private List<Ingrediente> listaIngredientes;

	public Comida(String nombreReceta, List<Ingrediente> listaIngredientes) {
		super(nombreReceta);
		if(listaIngredientes==null) {
			this.listaIngredientes = new ArrayList<>();
		}
		else {
			this.listaIngredientes = listaIngredientes;			
		}
	}

	public List<Ingrediente> getListaIngredientes() {
		return listaIngredientes;
	}

	public void setListaIngredientes(List<Ingrediente> listaIngredientes) {
		this.listaIngredientes = listaIngredientes;
	}

	@Override
	public Integer calculaCoccion() {
		return this.listaIngredientes.size();
	}

	@Override
	public Integer calculaCalorias() {
		
		return this.listaIngredientes.size()*3;
	}
	
	
	
	
}
