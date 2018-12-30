package br.com.codenation.desafio.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import br.com.codenation.desafio.annotations.AtributoNaoNulo;

/**
 * 
 * @author jether.rodrigues
 *
 */
public final class Time implements Serializable, Comparable<Time> {

	private static final long serialVersionUID = 1L;

	@AtributoNaoNulo
	private Long id;
	@AtributoNaoNulo
	private String nome;
	@AtributoNaoNulo
	private LocalDate dataCriacao;
	@AtributoNaoNulo
	private String corUniformePrincipal;
	private String corUniformeSecundario;
	
	public Time id(Long id) {
		this.id = id;
		return this;
	}
	
	public Time nome(String nome) {
		this.nome = nome;
		return this;
	}
	
	public Time dataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
		return this;
	}
	
	public Time corUniformePrincipal(String corUniformePrincipal) {
		this.corUniformePrincipal = corUniformePrincipal;
		return this;
	}
	
	public Time corUniformeSecundario(String corUniformeSecundario) {
		this.corUniformeSecundario = corUniformeSecundario;
		return this;
	}
	
	/*
	 * getters
	 */
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public String getCorUniformePrincipal() {
		return corUniformePrincipal;
	}

	public String getCorUniformeSecundario() {
		return corUniformeSecundario;
	}
	
	@Override
	public int compareTo(Time outroTime) {
		return this.id == outroTime.getId() ? 0 : -1;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(corUniformePrincipal, corUniformeSecundario, dataCriacao, id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Time other = (Time) obj;
		return Objects.equals(corUniformePrincipal, other.corUniformePrincipal)
				&& Objects.equals(corUniformeSecundario, other.corUniformeSecundario)
				&& Objects.equals(dataCriacao, other.dataCriacao) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Time [id=")
			.append(id)
			.append(", nome=")
			.append(nome)
			.append(", dataCriacao=")
			.append(dataCriacao)
			.append(", corUniformePrincipal=")
			.append(corUniformePrincipal)
			.append(", corUniformeSecundario=")
			.append(corUniformeSecundario)
			.append("]");
		return builder.toString();
	}
	
}
