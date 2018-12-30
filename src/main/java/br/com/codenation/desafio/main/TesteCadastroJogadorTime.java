package br.com.codenation.desafio.main;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import br.com.codenation.DesafioMeuTimeApplication;

/**
 * 
 * @author jether.rodrigues
 *
 */
public class TesteCadastroJogadorTime {
	
	private static DesafioMeuTimeApplication service = new DesafioMeuTimeApplication();
	
	public static void main(String[] args) {
		final long idTime = 1L;
		final long jogador1 = 1L;
		final long jogador2 = 2L;
		final int habilidade = 80;
		
		// Time
		cadastrarTime(idTime
				, "São Paulo Futebol Clube"
				, LocalDate.of(1930, Month.JANUARY, 25)
				, "Branco", "Vermelho");
		
		System.out.println();
		System.out.println(">>>> Times");
		listarTimesCadastrados();
		
		System.out.println();
		System.out.println(">>>> Times");
		imprimeNomeDoTime(idTime);
		
		// Jogadores
		cadastrarJogador(jogador1
				, idTime
				, "Jether Rodrigues do Nascimento"
				, LocalDate.of(1982, Month.JULY, 13)
				, 80, new BigDecimal(100000.0));
		
		cadastrarJogador(jogador2
				, idTime
				, "Denise Cristina C. C. Nascimento"
				, LocalDate.of(1982, Month.APRIL, 20)
				, 50, new BigDecimal(150000.0));
		
		defineCapitaoTime(jogador2);
		
		// Imprimindo
		System.out.println();
		System.out.println(">>>> Jogadores");
		listaJogadoresDoTime(idTime);
		
		System.out.println();
		System.out.println(">>>> Jogadores (toString())");
		service.imprimeJogadoresPorTime(idTime);
		
		System.out.println();
		System.out.println(">>>> Jogador - Capitao");
		imprimeCapitaoDoTime(idTime);
		
		System.out.println();
		System.out.println(">>>> Jogador - Nome");
		imprimeNomeDoJogador(jogador1);
		
		System.out.println();
		System.out.println(">>>> Jogador - Top");
		imprimeTopJogador(habilidade);
		
		System.out.println();
		System.out.println(">>>> Jogador - Salario");
		imprimeSalarioJogador(jogador2);
	}

	private static void imprimeNomeDoTime(long idTime) {
		System.out.println(String.format("O nome do Time é [ %s ].", service.buscarNomeTime(idTime)));
	}

	private static void imprimeSalarioJogador(long idJogador) {
		System.out.println(String.format("O salário do Jogador é [ %s ].", service.buscarSalarioDoJogador(idJogador).toPlainString()));
	}

	private static void imprimeTopJogador(int top) {
		service.buscarTopJogadores(top)
			.forEach(j -> System.out.println(String.format("Jogador [ %d ] ", j)));
	}

	private static void imprimeNomeDoJogador(long idJogador) {
		System.out.println(String.format("O nome do Jogador é [ %s ].", service.buscarNomeJogador(idJogador)));
	}

	private static void imprimeCapitaoDoTime(long idTime) {
		System.out.println(String.format("Jogador Capitao [ %d ] - Time [ %d ] ", service.buscarCapitaoDoTime(idTime), idTime));
	}

	private static void defineCapitaoTime(long idJogador) {
		service.definirCapitao(idJogador);
	}

	private static void listaJogadoresDoTime(long idTime) {
		service.buscarJogadoresDoTime(idTime)
			.forEach(j -> System.out.println(String.format("Jogador [ %d ] - Time [ %d ] ", j, idTime)));
	}
	
	private static void cadastrarJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		service.incluirJogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
	}
	
	private static void cadastrarTime(Long id, String nome, LocalDate criacao, String uniformePrincipal, String uniformeSecundario) {
		service.incluirTime(id, nome, criacao, uniformePrincipal, uniformeSecundario);
	}
	
	private static void listarTimesCadastrados() {
		service.buscarTimes().forEach(id -> System.out.println(String.format("Time [ %d ]", id)));
	}
}
