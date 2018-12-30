package br.com.codenation.desafio.annotations.validator;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Objects;

import br.com.codenation.desafio.annotations.NivelHabilidadeMaxMin;

/**
 * 
 * @author jether.rodrigues
 *
 */
public final class NivelHabilidadeMaxMinValidator implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public void validar(Object value) throws IllegalArgumentException, IllegalAccessException, Exception {
		Class<?> clazz = Objects.requireNonNull(value).getClass();
		for (Field field : clazz.getDeclaredFields()) {
			field.setAccessible(true);
			validaMaxMin(value, field);
		}
	}

	private void validaMaxMin(Object value, Field field)
			throws IllegalArgumentException, IllegalAccessException, Exception {
		if (field.isAnnotationPresent(NivelHabilidadeMaxMin.class)) {
			if (field.getType().equals(Integer.class)) {
				int toValidate = (int) field.get(value);
				if (toValidate < 0 ) {
					throw new Exception(
						String.format("O atributo [ %s ] não pode ser menor que 0. ", field.getName())
					);
				}
				
				if (toValidate > 100) {
					throw new Exception(
						String.format("O atributo [ %s ] não pode ser maior que 100. ", field.getName())
					);
				}
			}
		}
	}
}
