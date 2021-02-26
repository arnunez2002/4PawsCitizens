package co.edu.unbosque.fourPawsCitizens.model;

public class EmptyAttributeException extends Exception {

	String m;

	public EmptyAttributeException(String x) {
		super(x);
	}

	public EmptyAttributeException() {
	}

	public String AtributoIndefinido(String m) throws EmptyAttributeException {
		String mensaje = "";
		if (m.equals("")) {
			mensaje = "mal";
			if (m.isEmpty()) {
				mensaje = "mal";
			} else {
				mensaje = "bien";

			}

		} else {
			mensaje = "bien";

		}
		return mensaje;
	}
}
