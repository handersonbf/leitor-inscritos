package testes;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.Configuracoes;
import main.HTMLSource;
import main.ProcessadorDeInscritos;

public class TesteLeitor {
	private BufferedReader arquivoCSV = null;
	private int cont = 0;
	private HTMLSource html = new HTMLSource();
	private String HTML_GERADO = "<tr><td>0</td><td> Weber  Militão -  webe@gmail.com</td><td>G-Masculina</td></tr>";
	private String HTML_TAMANHO_NULO = "<meta http-equiv='Content-Type' content='text/html;charset=ISO-8859-1'><table><tr><td>Número</td><td>Nome</td><td>Blusa</td></tr></table>";
	private String texto = "648945680, 2017-07-14 20:53:35-03:00, Weber, Militão, webe@gmail.com, 1, JAVOU11 - COM CAMISA, 812356140, , Eventbrite Completed, BRL, 30.24, 2.24, 2.24, 0.00, Attending, , , , , , , Masculina G, Rua Nogueira Acioli,  3030, Centro, Fortaleza, CE, 6043567, BR, Analista de Sistemas,  Ceará";
	private String[] textoDoArquivoSeparado = texto.split(Configuracoes.SPLIT);
	private String tamanho = "Masculina G";

	@Before
	public void carregaArquivo() throws IOException {
		InputStream is = new FileInputStream("inscritos.csv");
		InputStreamReader isr = new InputStreamReader(is, Charset.forName("UTF-8"));
		arquivoCSV = new BufferedReader(isr);
	}
	
	@Test
	public void testaSeGerouHTML() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
		ProcessadorDeInscritos.gerarDados(cont, html, textoDoArquivoSeparado, tamanho);
		String htmlGerado = this.html.geraHTML();
		Assert.assertThat(htmlGerado, CoreMatchers.containsString(HTML_GERADO));
		
	}
	
	@Test
	public void testaSeOTamanhoDaBlusaForNULL() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		ProcessadorDeInscritos.gerarDados(cont, html, textoDoArquivoSeparado, null);
		String htmlGerado = this.html.geraHTML();
		Assert.assertThat(htmlGerado, CoreMatchers.containsString(HTML_TAMANHO_NULO));
	}
	
	@Test
	public void testaSeOTamanhoDaBlusaForVazio() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		ProcessadorDeInscritos.gerarDados(cont, html, textoDoArquivoSeparado, "");
		String htmlGerado = this.html.geraHTML();
		Assert.assertThat(htmlGerado, CoreMatchers.containsString(HTML_TAMANHO_NULO));
	}
}
