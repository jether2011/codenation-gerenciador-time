package br.com.codenation.desafio.util;

import java.io.Serializable;

import br.com.codenation.desafio.annotations.validator.AtributoNaoNuloValidator;
import br.com.codenation.desafio.annotations.validator.NivelHabilidadeMaxMinValidator;
import br.com.codenation.desafio.domain.Jogador;
import br.com.codenation.desafio.domain.Time;
import br.com.codenation.desafio.helper.ManipulaObjetos;

/**
 * 
 * @author jether.rodrigues
 *
 */
public final class Constante implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final ManipulaObjetos<Time> TIMES = new ManipulaObjetos<>();
	public static final ManipulaObjetos<Jogador> JOGADORES = new ManipulaObjetos<>();
	public static final AtributoNaoNuloValidator NAONULO = new AtributoNaoNuloValidator();
	public static final NivelHabilidadeMaxMinValidator HABILIDADE = new NivelHabilidadeMaxMinValidator();
}
