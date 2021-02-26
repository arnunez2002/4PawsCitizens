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
                    } catch (NumberFormatException s) {

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

                        } catch (Exception n) {
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

    public int countBySpecies(String especie) {
        int aux = 0;
        int total = 0;
        String contenido = "";
        for (int i = 0; i < petArray.size(); i++) {
            if (especie.equals(petArray.get(i).getSpecies())) {
                 aux++;
                 total = total+aux;

            }
        }

        return total;
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
            contenido = contenido + "ID: " + petArray.get(i).getId() + "\n" + "Species: " + petArray.get(i).getMicrochip() + "\n"
                    + "Gender: " + petArray.get(i).getSpecies() + "\n" + "Sexo: "+ petArray.get(i).getSex() + "\n" + "Size: " + petArray.get(i).getSize() + "\n"
                    + "Potentially Dangerous: " + petArray.get(i).getPotentiallyDangerous() + "\n" + "Neighborhood: " + petArray.get(i).getNeighborhood() + "\n" + "\n";
        }

        return contenido;
    }

    public String findByMultipleField(String sex, String species, String size, String potentDangerous) {
        String contador = "";

        String sex2 = sex.charAt(0)+"";
        String species2 = species.charAt(0)+"";
        String size2 = size.charAt(0)+"";
        String potentDangerous2 = potentDangerous.charAt(0)+"";
        String total= (sex2+species2+size2+potentDangerous2);

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
                contador = contador + petArray.get(i).getId()+"\n";

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

