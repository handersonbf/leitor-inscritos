package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class ProcessadorDeInscritos {

	private static final int POSICAO_BLUSA = Configuracoes.POSICAO_BLUSA;
	private static HTMLSource html = new HTMLSource();

	public static void inicializaLeitor() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		
		BufferedReader arquivoCSV = null;

		try {
			arquivoCSV = carregaArquivo();
			processandoArquivo(arquivoCSV);

			gravandoHTML();
			
			System.out.println(html.totalBlusas());
		} catch (FileNotFoundException e) {
			System.out.println("N�o foi poss�vel encontrar o arquivo.");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.out.println("Encode n�o suportado.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao tentar ler o arquivo.");
			e.printStackTrace();
		} finally {
			try {
				arquivoCSV.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void gravandoHTML() throws FileNotFoundException, IOException {
		OutputStream os = new FileOutputStream("inscritos.html", false);
		OutputStreamWriter osw = new OutputStreamWriter(os,Charset.forName("ISO-8859-15"));
		BufferedWriter bw = new BufferedWriter(osw);
		bw.newLine();
		bw.write(html.geraHTML());
		bw.write(html.totalBlusas());
		
		bw.close();
	}

	public static void processandoArquivo(BufferedReader arquivoCSV)
			throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		String linhaDoTextoDoCSV = arquivoCSV.readLine();
	
		processamentoDaLinhaCSV(arquivoCSV, linhaDoTextoDoCSV);
	}

	public static void processamentoDaLinhaCSV(BufferedReader arquivoCSV, String linhaDoTextoDoCSV)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
		
		for (int contadorDoFor = 0; linhaDoTextoDoCSV != null; contadorDoFor++) {
			String[] textoDoArquivoSeparado = linhaDoTextoDoCSV.split(Configuracoes.SPLIT);
			String tamanho = pegaTamanhoBlusaPelaPosicao(textoDoArquivoSeparado);
			
			gerarDados(contadorDoFor, html, textoDoArquivoSeparado, tamanho);
			linhaDoTextoDoCSV = arquivoCSV.readLine();
		}
		
	}
	
	public static BufferedReader carregaArquivo() throws IOException{
		BufferedReader br = null;
		InputStream is = new FileInputStream("inscritos.csv");
		InputStreamReader isr = new InputStreamReader(is, Charset.forName("UTF-8"));
		br = new BufferedReader(isr);
		return br;
	}
	
	public static String pegaTamanhoBlusaPelaPosicao(String[] textoDoArquivoSeparado){
		String tamanho = "";
		
		if(!textoDoArquivoSeparado[POSICAO_BLUSA].isEmpty()){
			tamanho = textoDoArquivoSeparado[POSICAO_BLUSA];
		}
		
		return tamanho;
	}

	private static void gerarDados(int cont, HTMLSource html, String[] textoDoArquivoSeparado, String tamanho)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		if(tamanho != null && !tamanho.isEmpty()){
			html.gerarDados(new Integer(cont), textoDoArquivoSeparado, POSICAO_BLUSA);
		}
	}
	
	
	
}
