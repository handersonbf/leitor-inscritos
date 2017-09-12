package testes;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.ProcessadorDeInscritos;

public class TesteLeitor {
	private BufferedReader arquivoCSV = null;

	@Before
	public void carregaArquivo() throws IOException {
		InputStream is = new FileInputStream("inscritos.csv");
		InputStreamReader isr = new InputStreamReader(is, Charset.forName("UTF-8"));
		arquivoCSV = new BufferedReader(isr);
	}
	
	@Test
	public void confirmaLeituraArquivo() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
		Assert.assertNotNull(arquivoCSV);
		//Leitor.processandoArquivo(arquivoCSV);
		
	}
}
