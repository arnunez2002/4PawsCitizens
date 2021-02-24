package co.edu.unbosque.fourPawsCitizens.controller;
import co.edu.unbosque.fourPawsCitizens.model.Manager;


import java.io.File;
import java.io.IOException;


public class Controller {

	Manager manager;
	File fileCSV = new File("pets-citizens.csv");

	public Controller() {
		manager = new Manager();
		//manager.uploadData();

		manager.setPetArray(manager.leerArchivo(fileCSV));
		manager.assignID();
		System.out.println(manager.mostrar(manager.getPetArray()));
	}
}
