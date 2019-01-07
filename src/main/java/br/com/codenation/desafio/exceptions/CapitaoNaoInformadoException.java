package br.com.codenation.desafio.exceptions;

/**
 * 
 * @author jether.rodrigues
 *
 */
public class CapitaoNaoInformadoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String MENSAGEM = "Capitão não informado!";
	
	public CapitaoNaoInformadoException() {
		super(MENSAGEM);
	}
}
