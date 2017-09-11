package testes;

import java.io.BufferedReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.Configuracoes;
import main.HTMLSource;
import main.Leitor;

public class TestandoArquivo {

	private static final String CLASSE_FEMININA_M = "FemininaM";
	private static final String CLASSE_MASCULINA_M = "MasculinaM";
	private static final String CLASSE_MASCULINA_G = "MasculinaG";
	private static final String CLASSE_MASCULINA_P = "MasculinaP";
	private static final String TAMANHO_BLUSA_MASCULINA = "Masculina P";
	private static final String REGEX_TAMANHO_BLUSA = Configuracoes.REGEX_MASCULINO;
	private static String LINHA_TEXTO_CSV = "";
	private static String[] TEXTO_DO_ARQUIVO_SEPARADO = null;
	private static final int POSICAO_BLUSA = Configuracoes.POSICAO_BLUSA;
	private static BufferedReader BR;
	private HTMLSource html = null;

	@Before
	public void init() throws IOException{
		BR = Leitor.carregaArquivo();
		LINHA_TEXTO_CSV = BR.readLine();
		TEXTO_DO_ARQUIVO_SEPARADO = LINHA_TEXTO_CSV.split(Configuracoes.SPLIT);
		this.html = new HTMLSource();
	}

	@Test
	public void testaColunaSePegouOTamanhoDaBlusa(){
		String tamanhoBlusa = TEXTO_DO_ARQUIVO_SEPARADO[POSICAO_BLUSA];
		boolean matches = tamanhoBlusa.matches(REGEX_TAMANHO_BLUSA);
		Assert.assertEquals("Não pegou a coluna certa", true,  matches);
	}
	
	@Test
	public void testaPegaTamanhoDaBlusa(){
		String pegaTamanhoBlusaPelaPosicao = Leitor.pegaTamanhoBlusaPelaPosicao(TEXTO_DO_ARQUIVO_SEPARADO);
		Assert.assertEquals("", TAMANHO_BLUSA_MASCULINA, pegaTamanhoBlusaPelaPosicao);
	}
	
	@Test
	public void validaSeTemCamisa(){
		html.setTextoDoArquivoSeparado(TEXTO_DO_ARQUIVO_SEPARADO);
		String validaSeTemCamisa = this.html.validaSeTemCamisa(POSICAO_BLUSA);
		boolean matches = validaSeTemCamisa.matches(REGEX_TAMANHO_BLUSA);
		Assert.assertEquals("Não pegou a coluna certa", true,  matches);
	}
	
	@Test
	public void validaSeTemCamisaEQualEh(){
		html.setTextoDoArquivoSeparado(TEXTO_DO_ARQUIVO_SEPARADO);
		String validaSeTemCamisa = this.html.validaSeTemCamisa(POSICAO_BLUSA);
		Assert.assertEquals("Era esperado M", TAMANHO_BLUSA_MASCULINA, validaSeTemCamisa);
		
	}
	
	@Test
	public void seTemCamisa(){
		html.setTextoDoArquivoSeparado(TEXTO_DO_ARQUIVO_SEPARADO);
		boolean semCamisa = this.html.isSEMCamisa();
		Assert.assertFalse(semCamisa);
	}
	
	@Test
	public void testaAlterarNomeTamanhoApenasM(){
		String alteraCampoParaNomeClasse = this.html.alteraCampoParaNomeClasse(TAMANHO_BLUSA_MASCULINA);
		Assert.assertEquals("", CLASSE_MASCULINA_P, alteraCampoParaNomeClasse);
	}
	
	@Test
	public void testaAlterarNomeTamanhoApenasG(){
		String alteraCampoParaNomeClasse = this.html.alteraCampoParaNomeClasse("G");
		Assert.assertEquals("", CLASSE_MASCULINA_G, alteraCampoParaNomeClasse);
	}
	
	@Test
	public void testaAlterarNomeTamanhoMasculinaM(){
		String alteraCampoParaNomeClasse = this.html.alteraCampoParaNomeClasse(Configuracoes.BLUSA_MASCULINA_M_NOMENCLATURA);
		Assert.assertEquals("", CLASSE_MASCULINA_M, alteraCampoParaNomeClasse);
	}
	
	@Test
	public void testaAlterarNomeTamanhoMasculinaG(){
		String alteraCampoParaNomeClasse = this.html.alteraCampoParaNomeClasse("Masculina G");
		Assert.assertEquals("", CLASSE_MASCULINA_G, alteraCampoParaNomeClasse);
	}
	
	@Test
	public void testaAlterarNomeTamanhoFemininaM(){
		String alteraCampoParaNomeClasse = this.html.alteraCampoParaNomeClasse(Configuracoes.BLUSA_FEMININA_M_NOMENCLATURA);
		Assert.assertEquals("", CLASSE_FEMININA_M, alteraCampoParaNomeClasse);
	}
	
	@Test
	public void testaAlterarNomeTamanhoFemininaBabyLookM(){
		String alteraCampoParaNomeClasse = this.html.alteraCampoParaNomeClasse("Babylook M");
		Assert.assertEquals("", CLASSE_FEMININA_M, alteraCampoParaNomeClasse);
	}
	
		
}
