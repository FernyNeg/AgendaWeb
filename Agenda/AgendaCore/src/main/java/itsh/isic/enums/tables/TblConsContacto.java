package itsh.isic.enums.tables;

public enum TblConsContacto {
	COL_IDCONTACTO("idContacto"), //
	COL_NOMBRE("Nombre"), //
	COL_PATERNO("aPaterno"), //
	COL_MATERNO("aMaterno"), //
	COL_USUARIO("Usuario"), //
	COL_CORREO("Correo"), //
	COL_CONTRASENIA("Contrasenia"), //
	COL_DIRECCION("Direccion"), //
	COL_CIUDAD("Ciudad"), //
	COL_PAIS("Pais"), //
	COL_CODPOSTAL("CodPostal"), //
	COL_STATUS("Status"), //
	COL_NOTAS("Notas"),//
	;

	private String descripcion;

	TblConsContacto(String descripcionEnt) {
		this.descripcion = descripcionEnt;
	}

	public String getDescripcion() {
		return descripcion;
	}

}
