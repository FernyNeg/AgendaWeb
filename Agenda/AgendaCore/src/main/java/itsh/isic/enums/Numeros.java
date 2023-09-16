package itsh.isic.enums;

public enum Numeros {

	CERO(0), UNO(1), DOS(2),;

	private Integer numero;

	Numeros(final Integer num) {
		this.numero = num;
	}

	public Integer getNumero() {
		return numero;
	}

}
