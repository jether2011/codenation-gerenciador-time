package br.com.codenation.desafio.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ManipulaObjetos<T> {
	private List<T> valores = new ArrayList<>();

	public void limparTudo() {
		valores.clear();
	}
	
	public void adicionar(T valor) {
		valores.add(valor);
	}

	public List<T> retornaValores() {
		return Collections.unmodifiableList(valores);
	}
}
