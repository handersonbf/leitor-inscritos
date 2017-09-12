package testes;

import org.junit.Assert;
import org.junit.Test;

import main.Configuracoes;
import main.ProcessadorDeInscritos;

public class TestaPosicaoCampoBlusa {

	private static String LINHA_TEXTO_CSV = "629449905,2017-05-15 12:44:03-03:00,Rafael,Mota Correia,rafaelmcdono@gmail.com,1,JAVOU10 - COM CAMISA,788772647,,Eventbrite Completed,BRL,30.24,2.24,2.24,0.00,Attending,,,,,,,Masculina M,,,,,61620040,BR,Desenvolvendor,Resource IT Solutions";
	private static String[] TEXTO_DO_ARQUIVO_SEPARADO = LINHA_TEXTO_CSV.split(Configuracoes.SPLIT);
	private static String LINHA_TEXTO_CSV_SEM_BLUSA = "Alan Gomes Silveira;alanomega@hotmail.com;;Casos de Sucesso - Com Blusa;200.300.907.1160;98277;30.80;30.8;;0;21/02/2017 23:22;ffbjavou09;22.55;(85) 98895-9581;alanomega@hotmail.com;(85)0000-0000;08/02/1994;FFB;Ensino médio;Estudante;Masculino;M";
	private static String[] TEXTO_DO_ARQUIVO_SEPARADO_SEM_BLUSA = LINHA_TEXTO_CSV_SEM_BLUSA.split(";");
	private static String LINHA_TEXTO_CSV_FEMININA = "633652855,2017-05-26 21:01:38-03:00,Kathleen,Oliveira,kattyolv@gmail.com,1,JAVOU10 - COM CAMISA,793864838,,Eventbrite Completed,BRL,30.24,2.24,2.24,0.00,Attending,,,,,,,Feminina M,,,,,62870000,BR,Assistente administrativo,";
	private static String[] TEXTO_DO_ARQUIVO_SEPARADO_FEMININA = LINHA_TEXTO_CSV_FEMININA.split(",");
	private static final String MASCULINA_M = Configuracoes.BLUSA_MASCULINA_M_NOMENCLATURA;
	private static final String FEMININA_M = Configuracoes.BLUSA_FEMININA_M_NOMENCLATURA;



	@Test
	public void testaColunaTamanhoDaBlusa(){
		String string = TEXTO_DO_ARQUIVO_SEPARADO[Configuracoes.POSICAO_BLUSA];
		
		
		Assert.assertEquals("A posicao da coluna do tamanho da blusa está errada", MASCULINA_M, string);
	}
	
	@Test
	public void testaColunaTamanhoDaBlusaFeminina(){
		String string = TEXTO_DO_ARQUIVO_SEPARADO_FEMININA[Configuracoes.POSICAO_BLUSA];
		
		Assert.assertEquals("A posicao da coluna do tamanho da blusa está errada", FEMININA_M, string);
	}
	
	@Test
	public void testaPegaTamanhoDaBlusa(){
		String pegaTamanhoBlusaPelaPosicao = ProcessadorDeInscritos.pegaTamanhoBlusaPelaPosicao(TEXTO_DO_ARQUIVO_SEPARADO);
		Assert.assertEquals("", MASCULINA_M, pegaTamanhoBlusaPelaPosicao);
	}
	
	
}
