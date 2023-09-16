package itsh.isic.excepsiones;

public class ServiciosException extends Exception {
	/*
	 * Clase generada para crear las exepciones generales sin imprimir texto
	 * exagerado a la hora de compilar e imprimir en el log
	 */
	private static final long serialVersionUID = -3179587548947498495L;
	private final String id;
	private final String mensaje;

	public ServiciosException(String id, String mensaje) {
		super(id);
		this.id = id;
		this.mensaje = mensaje;
	}

	public ServiciosException(String id, String mensaje, Throwable exc) {
		super(id, exc);
		this.mensaje = mensaje;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getMensaje() {
		return mensaje;
	}

}
