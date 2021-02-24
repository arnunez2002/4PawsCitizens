package co.edu.unbosque.fourPawsCitizens.model;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager {

	private ArrayList<Pet> petArray;

	public Manager() {
		petArray = new ArrayList<Pet>();
	}

	// Punto 2

	public ArrayList<Pet> uploadData() throws NumberFormatException, IOException {

		ArrayList<String> objetos = new ArrayList<String>();
		File excelFile = new File("microchipsPets.xlsx");
		FileInputStream fis = new FileInputStream(excelFile);
		XSSFWorkbook workBook = new XSSFWorkbook(fis);
		// sheet
		XSSFSheet sheet = workBook.getSheetAt(0);
		// row
		Iterator<Row> rowIt = sheet.iterator();
		Iterator<Cell> celdas;
		Row row;
		Cell celda;
		while (rowIt.hasNext()) {
			row = rowIt.next();
			celdas = row.iterator();
			while (celdas.hasNext()) {
				celda = celdas.next();
				objetos.add(celda.toString());
			}
		}
		fis.close();
		for (int i = 0; i < objetos.size(); i++) {
			Pet pet = new Pet();
			ArrayList<String> atributos = new ArrayList<String>();
			String linea = objetos.get(i);
			Scanner delimitar = new Scanner(linea);
			delimitar.useDelimiter("\\s*;\\s*");
			while (delimitar.hasNext()) {
				atributos.add(delimitar.next());
			}
			pet.setId("NO-ID");
			try {
				pet.setMicrochip(Long.parseLong(atributos.get(0)));
				pet.setSpecies(atributos.get(1));
				pet.setSex(atributos.get(2));
				pet.setSize(atributos.get(3));
				if (atributos.get(4).equals("NO")) {
					pet.setPotentiallyDangerous(false);
				} else {
					pet.setPotentiallyDangerous(true);
				}
				pet.setNeighborhood(atributos.get(5));
				petArray.add(pet);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return petArray;
	}

	// Punto 3

	public void assignID() {
		for (int i = 0; i < petArray.size(); i++) {
			String microChip = petArray.get(i).getMicrochip() + "";
			microChip = microChip.substring(microChip.length() - 3, microChip.length());
			String species = petArray.get(i).getSpecies().charAt(0) + "";
			String sex = petArray.get(i).getSex().charAt(0) + "";
			String size = petArray.get(i).getSize().charAt(0) + "";
			String potencialDanger = "";
			if (petArray.get(i).getPotentiallyDangerous()) {
				potencialDanger = "T";
			} else {
				potencialDanger = "F";
			}
			String neighborhood = petArray.get(i).getNeighborhood();
			String iD= microChip + "-" + species + sex + size + potencialDanger + "-" + neighborhood;
			petArray.get(i).setId(iD);
//			petArray.get(i).setId(microChip + "-" + species + sex + size + potencialDanger + "-" + neighborhood);
			int aux= 4;
			for (int j = 0; j < petArray.size(); j++) {
				if(j!=i) {
					while(iD.equals(petArray.get(j).getId())) {
					String f= 	petArray.get(i).getMicrochip()+"" ;
						microChip = f.substring(f.length() - aux, f.length());
						iD = microChip + "-" + species + sex + size + potencialDanger + "-" + neighborhood;
						petArray.get(i).setId(iD);
						aux++;	
					}
				}
			}
		}
	}
	
	
	
	
	
	
	public String mostrarPet(Pet pet) {
		String contenido = "";
			contenido = contenido +  pet.getId() + ";" + pet.getMicrochip() + ";"
					+ pet.getSpecies() + ";" + pet.getSex() + ";" + pet.getSize() + ";"
					+ pet.getPotentiallyDangerous() + ";" + pet.getNeighborhood() + "\n";
		return contenido;
	}
	
	

	public String mostrar(ArrayList<Pet> pets) {
		String contenido = "cantidad = " + pets.size() + "\n";
		for (int i = 0; i < pets.size(); i++) {
			contenido = contenido + "pos: " + i + " " + pets.get(i).getId() + ";" + pets.get(i).getMicrochip() + ";"
					+ pets.get(i).getSpecies() + ";" + pets.get(i).getSex() + ";" + pets.get(i).getSize() + ";"
					+ pets.get(i).getPotentiallyDangerous() + ";" + pets.get(i).getNeighborhood() + "\n";
		}
		return contenido;
	}

	public ArrayList<Pet> getPetArray() {
		return petArray;
	}

	public void setPetArray(ArrayList<Pet> petArray) {
		this.petArray = petArray;
	}
}
