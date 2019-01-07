package br.com.codenation.desafio.exceptions;

/**
 * 
 * @author jether.rodrigues
 *
 */
public class JogadorNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private static final String MENSAGEM = "Jogador não encontrado!";
	
	public JogadorNaoEncontradoException() {
		super(MENSAGEM);
	}
}
