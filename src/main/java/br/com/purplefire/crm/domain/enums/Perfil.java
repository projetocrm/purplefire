package br.com.purplefire.crm.domain.enums;

public enum Perfil {

	/*
	 * ADMIN(1, "ROLE_ADMIN"), CLIENTE(2, "ROLE_CLIENTE");
	 */

	USUARIO(1, "ROLE_USUARIO"), ADMINISTRADOR(2, "ROLE_ADMINISTRADOR"),
	CLIENTE_RESPONSAVEL(3, "ROLE_CLIENTE_RESPONSAVEL"), CLIENTE_REPRESENTANTE(4, "ROLE_CLIENTE_REPRESENTANTE");

	private int cod;
	private String descricao;

	private Perfil(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Perfil toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (Perfil x : Perfil.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
