package br.com.codenation.desafio.helper;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;

import br.com.codenation.desafio.domain.Time;
import static br.com.codenation.desafio.util.Constante.NAONULO;

/**
 * 
 * @author jether.rodrigues
 *
 */
public final class FabricaTime implements Serializable {

	private static final long serialVersionUID = 1L;

	public Optional<Time> retornaUmTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		Time time = new Time()
				.id(id)
				.nome(nome)
				.dataCriacao(dataCriacao)
				.corUniformePrincipal(corUniformePrincipal)
				.corUniformeSecundario(corUniformeSecundario);
		
		try {
			NAONULO.validar(time);
			
			return Optional.of(time);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Optional.empty();
		}	
	}
}
