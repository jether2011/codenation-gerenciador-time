package br.com.codenation.desafio.helper;

import static br.com.codenation.desafio.util.Constante.NAONULO;
import static br.com.codenation.desafio.util.Constante.HABILIDADE;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import br.com.codenation.desafio.domain.Jogador;

/**
 * 
 * @author jether.rodrigues
 *
 */
public final class FabricaJogador implements Serializable {

	private static final long serialVersionUID = 1L;

	public Optional<Jogador> retornarUmJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		Jogador jogador = new Jogador()
			.id(id)
			.idTime(idTime)
			.nome(nome)
			.dataNascimento(dataNascimento)
			.nivelHabilidade(nivelHabilidade)
			.salario(salario);
		
		try {
			HABILIDADE.validar(jogador);
			NAONULO.validar(jogador);
			
			return Optional.of(jogador);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Optional.empty();
		} 
	}
}
