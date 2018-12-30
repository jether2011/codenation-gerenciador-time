package br.com.codenation.desafio.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import br.com.codenation.desafio.annotations.AtributoNaoNulo;
import br.com.codenation.desafio.annotations.NivelHabilidadeMaxMin;
/**
 * 
 * @author jether.rodrigues
 *
 */
public final class Jogador implements Serializable, Comparable<Jogador> {

	private static final long serialVersionUID = 1L;

	@AtributoNaoNulo
	private Long id;
	@AtributoNaoNulo
	private Long idTime;
	@AtributoNaoNulo
	private String nome;
	@AtributoNaoNulo
	private LocalDate dataNascimento;
	@AtributoNaoNulo
	@NivelHabilidadeMaxMin
	private Integer nivelHabilidade;
	@AtributoNaoNulo
	private BigDecimal salario;
	
	private transient boolean capitao;
	
	public Jogador id(Long id) {
		this.id = id;
		return this;
	}
	
	public Jogador idTime(Long idTime) {
		this.idTime = idTime;
		return this;
	}
	
	public Jogador nome(String nome) {
		this.nome = nome;
		return this;
	}
	
	public Jogador dataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
		return this;
	}
	
	public Jogador nivelHabilidade(Integer nivelHabilidade) {
		this.nivelHabilidade = nivelHabilidade;
		return this;
	}
	
	public Jogador salario(BigDecimal salario) {
		this.salario = salario;
		return this;
	}
	
	public Jogador capitao(boolean capitao) {
		this.capitao = capitao;
		return this;
	}
	
	/*
	 * getters
	 */
	public Long getId() {
		return id;
	}

	public Long getIdTime() {
		return idTime;
	}

	public String getNome() {
		return nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public Integer getNivelHabilidade() {
		return nivelHabilidade;
	}

	public BigDecimal getSalario() {
		return salario;
	}
	
	public boolean ehCapitao() {
		return capitao;
	}

	@Override
	public int compareTo(Jogador outroJogador) {
		return this.id == outroJogador.getId() ? 0 : -1;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataNascimento, id, idTime, nivelHabilidade, nome, salario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jogador other = (Jogador) obj;
		return Objects.equals(dataNascimento, other.dataNascimento) 
				&& Objects.equals(id, other.id)
				&& Objects.equals(idTime, other.idTime) 
				&& Objects.equals(nivelHabilidade, other.nivelHabilidade)
				&& Objects.equals(nome, other.nome) 
				&& Objects.equals(salario, other.salario);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Jogador [id=")
			.append(id)
			.append(", idTime=")
			.append(idTime)
			.append(", nome=")
			.append(nome)
			.append(", dataNascimento=")
			.append(dataNascimento)
			.append(", nivelHabilidade=")
			.append(nivelHabilidade)
			.append(", salario=")
			.append(salario)
			.append(", capitao=")
			.append(capitao)
			.append("]");
		return builder.toString();
	}
	
}
