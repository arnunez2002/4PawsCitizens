package co.edu.unbosque.fourPawsCitizens.controller;
import co.edu.unbosque.fourPawsCitizens.model.Manager;


import java.io.File;
import java.io.IOException;


public class Controller {

	Manager manager;

	public Controller() {
		manager = new Manager();
		try {
			
			manager.uploadData();
			manager.assignID();
			System.out.println(manager.mostrar(manager.getPetArray()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
