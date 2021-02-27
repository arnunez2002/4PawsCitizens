package co.edu.unbosque.fourPawsCitizens.controller;

import co.edu.unbosque.fourPawsCitizens.model.EmptyAttributeException;
import co.edu.unbosque.fourPawsCitizens.model.Manager;
import co.edu.unbosque.fourPawsCitizens.model.Pet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Controller {

    Scanner in = new Scanner(System.in);
    Manager manager;
    File fileCSV = new File("pets-citizens.csv");
    ArrayList<Pet>pets;

    public Controller() throws EmptyAttributeException {
    	
        String deco = "---------------------------------------------------------------";
        manager = new Manager();

        // manager.uploadData();
        System.out.print(deco + "\n");
        System.out.print("*************** BIENVENIDO A PET CITIZENS ******************" + "\n");
        System.out.print(deco + "\n" + "\n");
        int num = 0;
        manager.setPetArray(manager.uploadData());
        Boolean aux = true;

        while (aux == true) {
            System.out.print("Opciones a realizar" + "\n");
            try {
                System.out.print("Salir" + "(0)" + "\n" + "Leer archivo" + "(1)" + "\n");
                num = in.nextInt();

                if (num <= 1 || num >= 0) {

                    if (num == 1) {
                        boolean exit = false;
                        while (exit == false) {
                            try {
                                int num2 = 0;
                                manager.uploadData();
                                System.out.println(manager.getPetArray().size());
                                System.out.print("-------------- OPCIONES A REALIZAE ---------------  " + "\n");
                                System.out.print("Salir (0)" + "\n" + "mostrar Archivo (1)" + "\n"
                                        + "Buscar por ID (2)" + "\n" + "Asignar IDs de animales (3)" + "\n"
                                        + "Buscar cantidad por especie (4)" + "\n"
                                        + "Buscar los potencialmente peligrosos de una localidad (5)" + "\n"
                                        + "Buscar por microschip (6)" + "\n" + "\n");
                                num2 = in.nextInt();

                                if (num2 < 0) {
                                    System.out.print("No se aceptan negativos" + "\n" + "\n");
                                }
                                if (num2 == 1) {
                                 
                               
                                    System.out.print(manager.mostrar(manager.getPetArray()));
                                    System.out.println(manager.getPetArray().size());
                                }
                                if (num2 == 2) {
                                    System.out.print("Escriba el microchip" + "\n");
                                    long num3 = in.nextLong();
                                    System.out.print(manager.findByMicrochip(num3));

                                }
                                if (num2 == 3) {
                                    manager.assignID();
                                    System.out.println(manager.mostrar(manager.getPetArray()));
                                }
                                if (num2 == 4) {
                                    System.out.print("Escriba la especie" + "\n");
                                    in.nextLine();
                                    String especie = in.nextLine();
                                    especie = especie.toUpperCase();
                                    System.out.println("la cantidad de "+especie + " es " +manager.countBySpecies(especie));
                                }
                                if (num2 == 5) {
                                    System.out.println("Introduce los datos de la siguiente manera" + "\n");
                                    System.out.print("cantidad de animales por buscar,TOP/LAST,localidad  " + "\n" + "\n");
                                    in.nextLine();
                                    String linea = in.nextLine();
                                    Scanner delimitar = new Scanner(linea);
                                    delimitar.useDelimiter("\\s*,\\s*");
                                    ArrayList<String> datos = new ArrayList<String>();
                                    while (delimitar.hasNext()) {
                                        datos.add(delimitar.next());
                                    }
                                    if (datos.get(0).matches("[0-9]*") && datos.get(1).equals("TOP")
                                            || datos.get(2).equals("LAST")) {
                                        System.out.println(manager.findBypotentDangerousInNeighborhood(
                                                Integer.parseInt(datos.get(0)), datos.get(1), datos.get(2)));
                                    }

                                }
                                if (num2 == 6) {
                                    manager.assignID();
                                    String sex = "";
                                    String species = "";
                                    String size = "";
                                    String potentDangerous = "";
                                    System.out.print("Escriba el microChip" + "\n");

                                    in.nextLine();
                                    System.out.print("Sex"+"\n");
                                    sex = in.nextLine();
                                    sex = sex.toUpperCase();

                                    System.out.print("species"+"\n");
                                    species = in.nextLine();
                                    species.toUpperCase();

                                    System.out.print("size"+"\n");
                                    size = in.nextLine();
                                    size.toUpperCase();

                                    System.out.print("potentDangerous"+"\n");
                                    potentDangerous = in.nextLine();
                                    potentDangerous = potentDangerous.toUpperCase();


                                    System.out.print(manager.findByMultipleField(sex, species, size, potentDangerous));
                                }
                                if (num2 == 0) {
                                    exit = true;
                                }
                            } catch (NumberFormatException en) {
                                System.out.print("Error de numero" + "\n");
                                break;
                            } catch (InputMismatchException en) {
                                System.out.print("Error de numero" + "\n");
                                break;
                            } 
//                            catch (IndexOutOfBoundsException en) {
//                                System.out.print("Error" + "\n");
//                            }
                        }
                    }
                    if (num == 0) {
                        System.out.print("Gracias");
                        aux = false;
                    }

                } else {
                    System.out.print("No ce acepta mayores a uno ni negativos");
                }
            } catch (NumberFormatException en) {
                System.out.print("Error de numero" + "\n");
                break;

            } catch (InputMismatchException en) {
                System.out.print("Error de numero" + "\n");
                break;

            }
        }
    }
}
