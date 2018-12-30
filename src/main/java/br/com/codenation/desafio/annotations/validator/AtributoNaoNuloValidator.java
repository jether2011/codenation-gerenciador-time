package br.com.codenation.desafio.annotations.validator;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Objects;

import br.com.codenation.desafio.annotations.AtributoNaoNulo;

/**
 * 
 * @author jether.rodrigues
 *
 */
public final class AtributoNaoNuloValidator implements Serializable {

	private static final long serialVersionUID = 1L;

	public void validar(Object value) throws IllegalArgumentException, IllegalAccessException, Exception {
		Class<?> clazz = Objects.requireNonNull(value).getClass();
		for (Field field : clazz.getDeclaredFields()) {
			field.setAccessible(true);
			validaSeEstaNulo(value, field);
		}
	}

	private void validaSeEstaNulo(Object value, Field field)
			throws IllegalArgumentException, IllegalAccessException, Exception {
		if (field.isAnnotationPresent(AtributoNaoNulo.class)) {
			if (field.get(value) == null) {
				throw new Exception(
					String.format("O atributo [ %s ] não pode ser nulo!", field.getName())
				);
			}
		}
	}

}
