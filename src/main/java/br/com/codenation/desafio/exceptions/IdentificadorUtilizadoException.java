package br.com.codenation.desafio.exceptions;

public class IdentificadorUtilizadoException extends RuntimeException {

	private static final long serialVersionUID = -3923652686289526049L;
	
	private static final String MENSAGEM = "O identificador já existe!";

	public IdentificadorUtilizadoException() {
		super(MENSAGEM);
	}
	
	
}
