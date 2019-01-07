package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.domain.Jogador;
import br.com.codenation.desafio.domain.Time;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.desafio.helper.Fabrica;
import br.com.codenation.desafio.helper.FabricaJogador;
import br.com.codenation.desafio.helper.FabricaTime;
import br.com.codenation.desafio.helper.ManipulaObjetos;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	private final Fabrica<Time> fabricaTime = new FabricaTime();
	private final Fabrica<Jogador> fabricaJogador = new FabricaJogador();
	
	private final ManipulaObjetos<Time> times = new ManipulaObjetos<>();
	private final ManipulaObjetos<Jogador> jogadores = new ManipulaObjetos<>();

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		if (recuperarTimePorId(id).isPresent())
			throw new IdentificadorUtilizadoException();
		
		times.adicionar(fabricaTime.fabricar(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario).get());
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		validaTimePeloIdentificador(idTime);
		
		if (recuperarJogadorPorId(id).isPresent())
			throw new IdentificadorUtilizadoException();

		jogadores.adicionar(fabricaJogador.fabricar(id, idTime, nome, dataNascimento, nivelHabilidade, salario).get());
		
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		Jogador jogador = recuperarJogadorPorId(idJogador)
				.orElseThrow(JogadorNaoEncontradoException::new);
		
		times.retornaValores()
				.parallelStream()
		        .filter(time -> time.getId().equals(jogador.getIdTime()))
		        .forEach(time -> time.capitao(jogador));
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		validaTimePeloIdentificador(idTime);
		
		Time time = recuperarTimePorId(idTime).get();
		
		if(time.getCapitao() == null)
			throw new CapitaoNaoInformadoException();
		
		return time.getIdCapitaoTime();
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		return recuperarJogadorPorId(idJogador)
				.orElseThrow(JogadorNaoEncontradoException::new)
				.getNome();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		return recuperarTimePorId(idTime)
				.orElseThrow(TimeNaoEncontradoException::new)
				.getNome();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		validaTimePeloIdentificador(idTime);
		
		return jogadores.retornaValores().parallelStream()
				.filter(j -> j.getIdTime().equals(idTime))
					.map(Jogador::getId)
					.sorted()
					.collect(Collectors.toList());
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		validaTimePeloIdentificador(idTime);
		
		Optional<Jogador> jogador =  jogadores.retornaValores()
					.parallelStream()
					.filter(j -> j.getIdTime().equals(idTime))
					.max(Comparator.comparing(Jogador::getNivelHabilidade));
		
		return jogador.isPresent() ? jogador.get().getId() : null;
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		validaTimePeloIdentificador(idTime);
		
		Optional<Jogador> jogador = jogadores.retornaValores()
					.parallelStream()
					.filter(j -> j.getIdTime().equals(idTime))
					.sorted(Comparator.comparingLong(Jogador::getId))
					.min(Comparator.comparing(Jogador::getDataNascimento));
		
		return jogador.isPresent() ? jogador.get().getId() : null; 
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		return times.retornaValores().parallelStream()
				.sorted(Comparator.comparingLong(Time::getId))
				.map(Time::getId)
				.collect(Collectors.toList());
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		validaTimePeloIdentificador(idTime);
		
		Optional<Jogador> jogador = jogadores.retornaValores().parallelStream()
					.filter(j -> j.getIdTime().equals(idTime))
					.sorted(Comparator.comparingLong(Jogador::getId))
					.max(Comparator.comparing(Jogador::getSalario));
		
		return jogador.isPresent() ? jogador.get().getId() : null;
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		return recuperarJogadorPorId(idJogador)
				.orElseThrow(JogadorNaoEncontradoException::new)
				.getSalario();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		return jogadores.retornaValores().parallelStream()
				.sorted(Comparator.comparingLong(Jogador::getNivelHabilidade)
						.reversed()
		                .thenComparingLong(Jogador::getId))
				.limit(top)
				.map(Jogador::getId)
				.collect(Collectors.toList());
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		Time deCasa = recuperarTimePorId(timeDaCasa)
						.orElseThrow(TimeNaoEncontradoException::new);
		
		Time deFora = recuperarTimePorId(timeDeFora)
						.orElseThrow(TimeNaoEncontradoException::new);
		
		return encontrarCorCamisaTimeDeFora(deCasa, deFora);
	}

	/*
	 * Métodos auxiliares
	 */
	private String encontrarCorCamisaTimeDeFora(Time deCasa, Time deFora) {
		return deCasa.getCorUniformePrincipal()
					.equals(deFora.getCorUniformePrincipal()) 
						? deFora.getCorUniformeSecundario() 
								: deFora.getCorUniformePrincipal();
	}
	
	private void validaTimePeloIdentificador(Long idTime) {
		recuperarTimePorId(idTime)
			.orElseThrow(TimeNaoEncontradoException::new);
	}
	
	private Optional<Time> recuperarTimePorId(Long idTime) {
		return times.retornaValores()
				.parallelStream()
				.filter(t -> t.getId().equals(idTime))
				.findAny();
	}

	private Optional<Jogador> recuperarJogadorPorId(Long idJogador) {
		return jogadores.retornaValores()
				.parallelStream()
				.filter(j -> j.getId().equals(idJogador))
				.findAny();
	}
}
