package co.edu.unbosque.fourPawsCitizens.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager {

	private ArrayList<Pet> petArray;

	public Manager() {
		petArray = new ArrayList<Pet>();
	}

	// Punto 2
/*
	public ArrayList<Pet> uploadData() throws NumberFormatException, IOException {

		ArrayList<String> objetos = new ArrayList<String>();
		File excelFile = new File("pets-citizens.csv");
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
*/

	public String escribirArchivo(File fArchivo, ArrayList<Pet> petArrayArrayList) {
		try {
			// Si el archivo NO existe se crea.

			if (fArchivo.exists()) {
				BufferedWriter bw = new BufferedWriter(new FileWriter(fArchivo));
				bw.write("");
				bw.close();

			} else {
				fArchivo.createNewFile();
			}
			// el atributo <true> significa que si existe el archivo, se a?aden
			// m?s registros sin borrar el archivo
			BufferedWriter fEscribir = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(fArchivo, true), "utf-8"));

			for (Pet pet : petArrayArrayList) {
				// escribe los datos en el archivo
				fEscribir.write(pet.getId()+ ";" + pet.getMicrochip() + ";" + pet.getSpecies() + ";"
						+ pet.getSex() +";"+pet.getSize()+";"+pet.getPotentiallyDangerous()+";"+pet.getNeighborhood()+ "\n");
				System.out.println(petArrayArrayList.size());
			}

			// Cierra el flujo de escritura
			fEscribir.close();
			return "Resultado exitoso";
		} catch (IOException ex) {
			// Captura un posible error
			System.out.println(ex.getMessage());
			return "Se ha presentado un error";

		}
	}

	public ArrayList<Pet> leerArchivo(File fArchivo) {
		try {

			if (fArchivo.exists()) {
				BufferedReader fLectura = new BufferedReader(new FileReader(fArchivo));
				ArrayList<Pet> listaPet = new ArrayList<Pet>();
				Scanner scanner;
				// se pasa el flujo al objeto scanner
				scanner = new Scanner(fArchivo);

				while (scanner.hasNextLine()) {
					// el objeto scanner lee linea a linea desde el archivo
					String linea = scanner.nextLine();
					Scanner delimitar = new Scanner(linea); // Parecido al split()
					// se usa una expresi?n regular
					// que valida que antes o despues de una coma (,) exista
					// cualquier cosa
					// parte la cadena recibida cada vez que encuentre una coma
					delimitar.useDelimiter("\\s*;\\s*");
					Pet e = new Pet();
					try {
						e.setMicrochip(Long.parseLong(delimitar.next()));
					}catch(NumberFormatException s){

					}
					e.setId("NO-ID");
					e.setSpecies(delimitar.next());
					e.setSex(delimitar.next());
					e.setSize(delimitar.next());
					if (delimitar.next().equals("NO")) {
						e.setPotentiallyDangerous(false);
					} else {
						e.setPotentiallyDangerous(true);
				try {
					e.setNeighborhood(delimitar.next());

					listaPet.add(e);
				}catch(Exception n) {
				System.out.print("Malo");
				}
					}
				}
				fLectura.close();
				return listaPet;

			} else {
				return new ArrayList<>();
			}
		} catch (IOException ex) {
			/* Captura un posible error y le imprime en pantalla */
			System.out.println(ex.getMessage());
			return null;
		}
	}


	// Punto 3

	public void assignID() {
		for (int i = 1; i < petArray.size(); i++) {
			String microChip = petArray.get(i).getMicrochip() + "";
			microChip = microChip.substring(microChip.length() - 3, microChip.length());
			String species = petArray.get(i).getSpecies().charAt(0) + "";
			String sex = petArray.get(i).getSex().charAt(0) + "";
			String size = petArray.get(i).getSize().charAt(0) + "";
			String potencialDanger = "";
			if (petArray.get(i).getPotentiallyDangerous()) {
				potencialDanger = "F";
			} else {
				potencialDanger = "T";
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
			contenido = contenido + pets.get(i).getId() + ";" + pets.get(i).getMicrochip() + ";"
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
