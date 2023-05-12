package programa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Comida;
import model.Ingrediente;

public class Programa {

	public static void main(String[] args) {
	
		File f = new File("src\\Receta.txt");

		try {
			List<Comida> listaComidas = new ArrayList<>();
			Scanner sc = new Scanner(f);
			sc.nextLine();
			while (sc.hasNextLine()) {
				String receta = sc.nextLine();
				String[] recetaFormat = receta.split("#");
				String nombreReceta = recetaFormat[0];
				String[] ingredientes = recetaFormat[1].split("-");
				List<Ingrediente> listaIngredientes = new ArrayList<>();
				System.out.println("Nombre de la receta: "+nombreReceta+"\nIngredientes:");
				for(String ing: ingredientes) {
					String[] ingredientesAtributos = ing.split("%");
					Ingrediente ingred = new Ingrediente();
					for(String ia: ingredientesAtributos) {
						if(ia.contains("NOM")) {
							ingred.setNombreIngrediente(ia.replace("NOM", ""));
						}
						else if(ia.contains("CAN")) {
							ingred.setCantidad(ia.replace("CAN", ""));
						}
						else if(ia.contains("MED")){
							ingred.setMedida(ia.replace("MED", ""));
						}
					}
					System.out.println(ingred);
					listaIngredientes.add(ingred);
				}
				Comida comida = new Comida(nombreReceta, listaIngredientes);
				listaComidas.add(comida);
				System.out.println("-------------------------------------");
			}
			sc.close();
			
			String contenidoJenkinsFile = 
					"pipeline {\n" +
	                        "    agent any\n" +
	                        "    stages {\n" +
	                        "        stage('Build') {\n" +
	                        "            steps {\n" +
	                        "                script {\n" +
	                        "                    echo 'Building Recetas (Build #" + 1 + ")'\n" +
	                        "                }\n" +
	                        "            }\n" +
	                        "        }\n";
			
			
			for(Comida c: listaComidas) {
				contenidoJenkinsFile +="        stage('"+c.getNombreReceta()+"') {\n" +
	                    				"            steps {\n" +
					                    "                script {\n" +
					                    "                    echo 'Ingredientes de la receta "+c.getNombreReceta()+": es:'\n";
				for(Ingrediente i: c.getListaIngredientes()) {
					contenidoJenkinsFile +="                    echo 'Nombre: "+i.getNombreIngrediente()+" Cantidad: "+i.getCantidad()
					+" Medida: "+i.getMedida()+"'\n";
				}
				contenidoJenkinsFile +="                    echo 'Tiempo de coccion: "+c.calculaCoccion()+" Calorias: "+c.calculaCalorias()+"'\n";
				contenidoJenkinsFile +="                }\n" +
					                    "            }\n" +
					                    "        }\n";
			}

			
			contenidoJenkinsFile += "        stage('Deploy') {\n" +
	                        "            steps {\n" +
	                        "                script {\n" +
	                        "                    echo 'Deployment activo...'\n" +
	                        "                }\n" +
	                        "            }\n" +
	                        "        }\n" +
	                        "    }\n" +
	                        "}";
			
			try (
					PrintWriter writer = new PrintWriter(new FileWriter("Jenkinsfile")))
				{
					writer.write(contenidoJenkinsFile);
					System.out.println("Se genero el Jenkinsfile");
				} 
				
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
