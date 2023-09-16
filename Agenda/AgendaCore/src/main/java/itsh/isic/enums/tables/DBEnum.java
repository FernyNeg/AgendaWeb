package itsh.isic.enums.tables;

public enum DBEnum {
	CONTACTO("contacto"), //
	;

	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	private DBEnum(String descripcion) {
		this.descripcion = descripcion;
	}
}
