package br.com.codenation.desafio.helper;

import java.io.Serializable;
import java.util.Optional;

/**
 * 
 * @author jether.rodrigues
 *
 */
public interface Fabrica<T> extends Serializable {
	Optional<T> fabricar(Object... valores);
}
