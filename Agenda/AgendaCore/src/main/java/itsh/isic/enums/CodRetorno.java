package itsh.isic.enums;

public enum CodRetorno {
	CEROS("0000"), //
	FALTAN_PARAMS("0001"), //
	PARAMS_REPETIDOS("0002"), //

	FALLO_SERVER("0500"),;

	private String valor;

	public String getDescripcion() {
		return valor;
	}

	private CodRetorno(String entrada) {
		this.valor = entrada;
	}
}
