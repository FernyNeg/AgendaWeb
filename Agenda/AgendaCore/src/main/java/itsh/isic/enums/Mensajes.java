package itsh.isic.enums;

public enum Mensajes {
	// Errores en services
	CONTACTO_NULL("El id del contacto no puede estar vacio"), //
	CONTACTO_USU_NULL("El usuario del contacto no puede estar vacio y ser menor de 6 caracteres"), //
	CONTACTO_CORREO_NULL("El correo del contacto no puede estar vacio"), //
	CONTACTO_CONTRASENIA_NULL("La contraseña del contacto no puede estar vacia"), //
	CONTACTO_NOMBRE_NULL("El nombre del contacto no puede estar vacio"), //
	CORREO_REPETIDO("Ya existe el correo en los registros"), //
	USUARIO_REPETIDO("Ya existe el usuario en los registros"), //

	// Operaciones Completas
	OP_COMPLETADA("Operación completada con exito"), //
	
	// consultas vacias
	EMPTY_RES_LIST("No se encontraron datos de la consulta"), //
	EMPTY_RES_OBJ("No se encontró el dato de la consulta"), //

	// Mensajes de servidor
	FALLO_SERVER("Error interno del servidor"), //
	; //

	private String descripcion;

	Mensajes(String descripcionEnt) {
		this.descripcion = descripcionEnt;
	}

	public String getDescripcion() {
		return descripcion;
	}

}
