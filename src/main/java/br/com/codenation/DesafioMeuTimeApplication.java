package br.com.codenation;

import static br.com.codenation.desafio.util.Constante.JOGADORES;
import static br.com.codenation.desafio.util.Constante.TIMES;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.domain.Jogador;
import br.com.codenation.desafio.domain.Time;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.desafio.helper.FabricaJogador;
import br.com.codenation.desafio.helper.FabricaTime;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	private final FabricaTime fabricaTime = new FabricaTime();
	private final FabricaJogador fabricaJogador = new FabricaJogador();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.codenation.desafio.app.MeuTimeInterface#incluirTime(java.lang.Long,
	 * java.lang.String, java.time.LocalDate, java.lang.String, java.lang.String)
	 */
	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		Optional<Time> time = fabricaTime.retornaUmTime(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);

		if (time.isPresent()) {
			if (verificaSeTimeExiste(time.get()))
				throw new IdentificadorUtilizadoException();

			TIMES.adicionar(time.get());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.codenation.desafio.app.MeuTimeInterface#incluirJogador(java.lang.Long,
	 * java.lang.Long, java.lang.String, java.time.LocalDate, java.lang.Integer,
	 * java.math.BigDecimal)
	 */
	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {

		if (!verificaSeTimeExistePeloIdentificador(idTime))
			throw new TimeNaoEncontradoException();

		Optional<Jogador> jogador = fabricaJogador.retornarUmJogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);

		if (jogador.isPresent()) {
			if (verificaSeJogadorExiste(jogador.get()))
				throw new IdentificadorUtilizadoException();

			JOGADORES.adicionar(jogador.get());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.codenation.desafio.app.MeuTimeInterface#definirCapitao(java.lang.Long)
	 */
	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		//TODO: aqui antes de definir o jogador como capitao, é necessário verificar se o time já possui um capitao. Se sim, definir o jogador atual como
		// capitao = false e somente então setar o novo capitao
		
		Jogador jogador = JOGADORES.retornaValores()
			.parallelStream()
			.filter(j -> j.getId() == idJogador)
			.findAny()
			.get();
		
		jogador.capitao(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.codenation.desafio.app.MeuTimeInterface#buscarCapitaoDoTime(java.lang.
	 * Long)
	 */
	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		return JOGADORES.retornaValores()
				.parallelStream()
				.filter(j -> j.getIdTime() == idTime)
				.map(j -> j)
				.collect(Collectors.toList())
					.parallelStream()
					.filter(j -> j.ehCapitao() == true)
					.mapToLong(j -> j.getId())
					.findAny()
					.getAsLong();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.codenation.desafio.app.MeuTimeInterface#buscarNomeJogador(java.lang.
	 * Long)
	 */
	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		return JOGADORES.retornaValores()
				.parallelStream()
				.filter(j -> j.getId() == idJogador)
				.findAny()
				.get()
				.getNome();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.codenation.desafio.app.MeuTimeInterface#buscarNomeTime(java.lang.Long)
	 */
	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		return TIMES.retornaValores()
				.parallelStream()
				.filter(t -> t.getId() == idTime)
				.findAny()
				.get()
				.getNome();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.codenation.desafio.app.MeuTimeInterface#buscarJogadoresDoTime(java.
	 * lang.Long)
	 */
	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		return JOGADORES.retornaValores()
				.parallelStream()
				.filter(j -> j.getIdTime() == idTime)
				.map(j -> j.getId())
				.collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.codenation.desafio.app.MeuTimeInterface#buscarMelhorJogadorDoTime(java
	 * .lang.Long)
	 */
	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.codenation.desafio.app.MeuTimeInterface#buscarJogadorMaisVelho(java.
	 * lang.Long)
	 */
	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.codenation.desafio.app.MeuTimeInterface#buscarTimes()
	 */
	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		return TIMES.retornaValores()
				.parallelStream()
				.map(t -> t.getId())
				.collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.codenation.desafio.app.MeuTimeInterface#buscarJogadorMaiorSalario(java
	 * .lang.Long)
	 */
	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.codenation.desafio.app.MeuTimeInterface#buscarSalarioDoJogador(java.
	 * lang.Long)
	 */
	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		return JOGADORES.retornaValores()
				.parallelStream()
				.filter(j -> j.getId() == idJogador)
				.findAny()
				.get()
				.getSalario();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.codenation.desafio.app.MeuTimeInterface#buscarTopJogadores(java.lang.
	 * Integer)
	 */
	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		return JOGADORES.retornaValores()
				.parallelStream()
				.filter(j -> j.getNivelHabilidade() == top)
				.map(j -> j.getId())
				.collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.codenation.desafio.app.MeuTimeInterface#buscarCorCamisaTimeDeFora(java
	 * .lang.Long, java.lang.Long)
	 */
	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
//		return TIMES.retornaValores()
//				.parallelStream()
//				.filter(t -> t.getId() == timeDeFora)
//				.findAny()
//				.get()
//				.getCorUniformeSecundario();
		return null;
	}

	public void imprimeJogadoresPorTime(Long idTime) {
		JOGADORES.retornaValores()
			.parallelStream()
			.filter(j -> j.getIdTime() == idTime)
			.collect(Collectors.toList())
			.forEach(jogador -> System.out.println(jogador.toString()));
	}
	
	private boolean verificaSeTimeExiste(Time time) {
		return TIMES.retornaValores()
				.parallelStream()
				.filter(t -> t.getId() == time.getId())
				.findAny()
				.isPresent();
	}

	private boolean verificaSeTimeExistePeloIdentificador(Long idTime) {
		return TIMES.retornaValores()
				.parallelStream()
				.filter(t -> t.getId() == idTime)
				.findAny()
				.isPresent();
	}

	private boolean verificaSeJogadorExiste(Jogador jogador) {
		return JOGADORES.retornaValores()
				.parallelStream()
				.filter(j -> j.getId() == jogador.getId())
				.findAny()
				.isPresent();
	}
}
