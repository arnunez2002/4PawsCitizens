package co.edu.unbosque.fourPawsCitizens.model;

public class Pet {

	private String id;
	private long microchip;
	private String species;
	private String sex;
	private String size;
	private Boolean potentiallyDangerous;
	private String neighborhood;

	public Pet() {

	}

	public Pet(String id, long microchip, String species, String sex, String size, Boolean potentiallyDangerous,
			String neighborhood) {

		this.id = id;
		this.microchip = microchip;
		this.species = species;
		this.sex = sex;
		this.size = size;
		this.potentiallyDangerous = potentiallyDangerous;
		this.neighborhood = neighborhood;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getMicrochip() {
		return microchip;
	}

	public String setMicrochip(long microchip) throws NumberFormatException {
		String contenido = "";
		try {
			this.microchip = microchip;
		} catch (Exception e) {
			// TODO: handle exception
			return "Error en el micro";
		}
		return contenido;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Boolean getPotentiallyDangerous() {
		return potentiallyDangerous;
	}

	public void setPotentiallyDangerous(Boolean potentiallyDangerous) throws EmptyAttributeException {

		String mensaje = "....";
		try {
			if (potentiallyDangerous.equals("")) {
				mensaje = "localidad vacia";
				throw new EmptyAttributeException("localidad vacia");
			}else {
				this.potentiallyDangerous = potentiallyDangerous;
				mensaje = "Pokemon Registrado";
			}
		} catch (Exception e) {
		}

		this.potentiallyDangerous = potentiallyDangerous;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

}
