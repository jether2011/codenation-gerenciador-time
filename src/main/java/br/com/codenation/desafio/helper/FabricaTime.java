package br.com.codenation.desafio.helper;

import java.time.LocalDate;
import java.util.Optional;

import br.com.codenation.desafio.annotations.validator.AtributoNaoNuloValidator;
import br.com.codenation.desafio.domain.Time;

/**
 * 
 * @author jether.rodrigues
 *
 */
public final class FabricaTime implements Fabrica<Time> {

	private static final long serialVersionUID = 1L;
	
	private final AtributoNaoNuloValidator naoNulo = new AtributoNaoNuloValidator();

	public Optional<Time> fabricar(Object... valores) {
		Time time = new Time()
				.id((Long) valores[0])
				.nome((String) valores[1])
				.dataCriacao((LocalDate) valores[2])
				.corUniformePrincipal((String) valores[3])
				.corUniformeSecundario((String) valores[4]);
		
		try {
			naoNulo.validar(time);
			
			return Optional.of(time);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Optional.empty();
		}	
	}
}
