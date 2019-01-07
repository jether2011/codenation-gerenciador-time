package br.com.codenation.desafio.helper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import br.com.codenation.desafio.annotations.validator.AtributoNaoNuloValidator;
import br.com.codenation.desafio.annotations.validator.NivelHabilidadeMaxMinValidator;
import br.com.codenation.desafio.domain.Jogador;

/**
 * 
 * @author jether.rodrigues
 *
 */
public final class FabricaJogador implements Fabrica<Jogador> {

	private static final long serialVersionUID = 1L;
	
	private final AtributoNaoNuloValidator naoNulo = new AtributoNaoNuloValidator();
	private final NivelHabilidadeMaxMinValidator habilidade = new NivelHabilidadeMaxMinValidator();

	public Optional<Jogador> fabricar(Object... valores) {
		Jogador jogador = new Jogador()
			.id((Long) valores[0])
			.idTime((Long) valores[1])
			.nome((String) valores[2])
			.dataNascimento((LocalDate) valores[3])
			.nivelHabilidade((Integer) valores[4])
			.salario((BigDecimal) valores[5]);
		
		try {
			habilidade.validar(jogador);
			naoNulo.validar(jogador);
			
			return Optional.of(jogador);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Optional.empty();
		} 
	}
}
