package br.com.codenation.desafio.exceptions;

public class TimeNaoEncontradoException extends RuntimeException {
	
	private static final long serialVersionUID = -3923652686289526049L;

	private static final String MENSAGEM = "Time não encontrado!";

	public TimeNaoEncontradoException() {
		super(MENSAGEM);
	}
	
}
