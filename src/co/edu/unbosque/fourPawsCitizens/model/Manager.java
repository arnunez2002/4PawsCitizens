package co.edu.unbosque.fourPawsCitizens.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class Manager {

    private ArrayList<Pet> petArray;

    public Manager() {
        petArray = new ArrayList<Pet>();
    }

    // Punto 2

    public ArrayList<Pet> uploadData() throws EmptyAttributeException {

        int a = 0;
        File fArchivo = new File("pets-citizens.csv");
        ArrayList<Pet> listaPet = new ArrayList<Pet>();
        try {

            BufferedReader fLectura = new BufferedReader(new FileReader(fArchivo));

            Scanner scanner;
            // se pasa el flujo al objeto scanner
            scanner = new Scanner(fArchivo);
            int contador = 0;
            while (scanner.hasNextLine()) {

                ArrayList<String> atributos = new ArrayList<String>();
                try {


                    String linea = scanner.nextLine();
                    Scanner delimitar = new Scanner(linea);
                    int a1 = 0;
                    delimitar.useDelimiter("\\s*;\\s*");
                    while (delimitar.hasNext()) {
                        atributos.add(delimitar.next());
//							System.out.print("pos: "+contador+" " +atributos.get(a1)+ " ");
                        a1++;
                    }


//                		System.out.println(atributos.size());
                    if (atributos.size() == 6) {
                        if (atributos.get(0).matches("[0-9]+")) {
                            if (atributos.get(1).isEmpty() == false && atributos.get(2).isEmpty() == false && atributos.get(3).isEmpty() == false && atributos.get(3).isEmpty() == false && atributos.get(4).isEmpty() == false && atributos.get(atributos.size() - 1).isEmpty() == false) {
                                Pet e = new Pet();

                                e.setId("NO-ID");
                                e.setMicrochip(Long.parseLong(atributos.get(0)));
                                e.setSpecies(atributos.get(1));
                                e.setSex(atributos.get(2));
                                e.setSize(atributos.get(3));
                                if (atributos.get(4).equals("NO")) {
                                    e.setPotentiallyDangerous(false);
                                } else {
                                    e.setPotentiallyDangerous(true);
                                }
                                if (atributos.get(5).equals("") == false) {
                                    e.setNeighborhood(atributos.get(5));
                                }
//                					System.out.println(mostrarPet(e)+"		pos: "+ contador);
                                contador++;
                                listaPet.add(e);

                            } else {
//                					System.out.println("Datos vacios   ----------------------------");
                            }
                        } else {
//                				System.out.println("Erro en micro   -------------------------------");
                        }
                    }


                } catch (EmptyAttributeException s) {
                    System.out.println("lllllllllllllllllllllllllllll");
                } catch (NumberFormatException s) {
                    System.out.println(s + "RRRRRRRRRR   ");
                }

//

            }
            fLectura.close();
            return listaPet;

        } catch (IOException ex) {

        }
        return listaPet;
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
                potencialDanger = "NO";
            } else {
                potencialDanger = "SI";
            }
            String neighborhood = petArray.get(i).getNeighborhood();
            String iD = microChip + "-" + species + sex + size + potencialDanger + "-" + neighborhood;
            petArray.get(i).setId(iD);
//			petArray.get(i).setId(microChip + "-" + species + sex + size + potencialDanger + "-" + neighborhood);
            int aux = 4;
            for (int j = 0; j < petArray.size(); j++) {
                if (j != i) {
                    while (iD.equals(petArray.get(j).getId())) {
                        String f = petArray.get(i).getMicrochip() + "";
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
        contenido = contenido + pet.getId() + ";" + pet.getMicrochip() + ";"
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

    public String findByMicrochip(long num) {
        String contenido = "";
        for (int i = 0; i < petArray.size(); i++) {
            if (num == petArray.get(i).getMicrochip()) {
                contenido = "ID: " + petArray.get(i).getId() + "\n" + "Species: " + petArray.get(i).getMicrochip() + "\n"
                        + "Gender: " + petArray.get(i).getSpecies() + "\n" + petArray.get(i).getSex() + "\n" + "Size: " + petArray.get(i).getSize() + "\n"
                        + "Potentially Dangerous: " + petArray.get(i).getPotentiallyDangerous() + "\n" + "Neighborhood: " + petArray.get(i).getNeighborhood() + "\n";
            }
        }

        return contenido;
    }

    public String countBySpecies(String especie) {
        int aux = 0;
        int total = 0;
        String contenido = "";
        for (int i = 0; i < petArray.size(); i++) {
            if (especie.equals(petArray.get(i).getSpecies())) {
                aux++;

            }

        }
        contenido = "La cantidad de " + especie + " " + aux;

        return contenido;
    }

    public String findBypotentDangerousInNeighborhood(int n, String range, String neighborhood) {
        ArrayList<Pet> pets = new ArrayList<Pet>();
        if (range.equals("TOP")) {
            for (int i = 0; i < petArray.size(); i++) {
                if (petArray.get(i).getPotentiallyDangerous() == true && petArray.get(i).getNeighborhood().equals(neighborhood)) {
                    pets.add(petArray.get(i));
                }
            }
        } else if (range.equals("LAST")) {
            for (int i = petArray.size(); i > 0; i--) {
                if (petArray.get(i).getPotentiallyDangerous() == true && petArray.get(i).getNeighborhood().equals(neighborhood)) {
                    pets.add(petArray.get(i));
                }
            }
        }
        String contenido = "";
        for (int i = 0; i < n; i++) {
            contenido = contenido + "ID: " + pets.get(i).getId() + "\n" + "Species: " + pets.get(i).getSpecies() + "\n"
                    + "Gender: " + pets.get(i).getSex() + "\n" + "Size: " + pets.get(i).getSize() + "\n"
                    + "Potentially Dangerous: " + pets.get(i).getPotentiallyDangerous() + "\n" + "Neighborhood: " + pets.get(i).getNeighborhood() + "\n" + "\n";
        }

        return contenido;
    }

    public String findByMultipleField(String sex, String species, String size, String potentDangerous) {
        String contador = "";

        String sex2 = sex.charAt(0) + "";
        String species2 = species.charAt(0) + "";
        String size2 = size.charAt(0) + "";
        String potentDangerous2 = potentDangerous.charAt(0) + "";
        String total = (sex2 + species2 + size2 + potentDangerous2);

        for (int i = 0; i < petArray.size(); i++) {

            String species1 = petArray.get(i).getSpecies().charAt(0) + "";
            String sex1 = petArray.get(i).getSex().charAt(0) + "";
            String size1 = petArray.get(i).getSize().charAt(0) + "";
            String potencialDanger = "";
            if (petArray.get(i).getPotentiallyDangerous().equals("SI")) {
                potencialDanger = "S";
            } else {
                potencialDanger = "N";
            }
            String iD = sex1 + species1 + size1 + potencialDanger;
            if (total.equals(iD)) {
                contador = contador + petArray.get(i).getId() + "\n";

            }
        }
        return contador;
    }

    public ArrayList<Pet> getPetArray() {
        return petArray;
    }

    public void setPetArray(ArrayList<Pet> petArray) {
        this.petArray = petArray;
    }
}

