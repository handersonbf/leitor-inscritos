package main;

import java.util.ArrayList;
import java.util.List;

public class HTMLSource {
	private static final String PACOTE_BLUSA = "main.Blusa";
	private static final int COLUNA_EMAIL = Configuracoes.COLUNA_EMAIL;
	private static final int COLUNA_DONO_PAY_PAL =  Configuracoes.COLUNA_DONO_PAY_PAL;
	private static final int COLUNA_NOME =  Configuracoes.COLUNA_NOME;
	private static final int COLUNA_SOBRE_NOME =  Configuracoes.COLUNA_SOBRE_NOME;
	private static final int COLUNA_NOME_TICKET =  Configuracoes.COLUNA_NOME_TICKET;
	private static final String TEXTO_CAMPO_SEM_BLUSA =  Configuracoes.TEXTO_CAMPO_SEM_BLUSA;
	private StringBuilder stringsHTML = new StringBuilder();
	private StringBuilder totalBlusas = new StringBuilder();
	private Integer BLUSA_TOTAL = 0;
	private List<String> semNomes = new ArrayList<>();
	private String[] textoDoArquivoSeparado;
	
	public void setTextoDoArquivoSeparado(String[] textoDoArquivoSeparado) {
		this.textoDoArquivoSeparado = textoDoArquivoSeparado;
	}
	
	public HTMLSource(){
		stringsHTML.append("<meta http-equiv='Content-Type' content='text/html;charset=ISO-8859-1'>");
		stringsHTML.append("<table>").append("<tr><td>Número</td><td>Nome</td><td>Blusa</td></tr>");
	}
	
	public void setarValores(Integer numero, String nome, Blusa blusa){
		stringsHTML.append("<tr>")
		.append("<td>").append(numero).append("</td>");
		stringsHTML.append("<td>");
		if(isFeminino()){
			stringsHTML.append("<font color='#FF00FF'><strong>").append(nome).append("</strong></font>").append("</td>");
		}else {
			stringsHTML.append(nome).append("</td>");
		}
		
		stringsHTML.append("<td>").append(blusa.toString()).append("</td>").append("</tr>");
		blusa.contaBlusas();
		
		if(!isSEMCamisa()){
			BLUSA_TOTAL++;
		}
	}
	
	public String validaSeTemCamisa(int posicaoArray){
		String tamanhoCamisa= Configuracoes.NOME_CLASSE_SEM_BLUSA;
		if(!isSEMCamisa()){
			return tamanhoCamisa = textoDoArquivoSeparado[posicaoArray];
		}
		return tamanhoCamisa;
	}
	
	public boolean isSEMCamisa(){
		return this.textoDoArquivoSeparado[COLUNA_NOME_TICKET].contains(TEXTO_CAMPO_SEM_BLUSA);
	}
	
	public boolean isFeminino(){
		return this.textoDoArquivoSeparado[Configuracoes.POSICAO_BLUSA].contains("Femini");
	}
	
	public void gerarDados(Integer numero, String[] textoDoArquivoSeparado, int posicaoArray) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		this.textoDoArquivoSeparado = textoDoArquivoSeparado;
		String texto = "";
		String nome = validaSeTemCamisa(posicaoArray);
		if(regexNome(nome).equals(Configuracoes.NOME_CLASSE_SEM_BLUSA)){
			texto = "";
		}else{
			texto = this.textoDoArquivoSeparado[posicaoArray].replace("no", "na");
		}
		
		carregarClasseBlusa(numero, texto);
	}

	public void carregarClasseBlusa(Integer numero, String texto)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class classe = Class.forName(PACOTE_BLUSA  + alteraCampoParaNomeClasse(texto));
		Object o = classe.newInstance();
		Blusa blusa = (Blusa) o;
		this.setarValores(numero, validaSePossuiNome(), blusa);
	}
	
	public String regexNome(String nome){
		return nome.replace("\"", "")
				.replace("-", "")
				.replace(" ", "")
				.replace("no", "na");
	}
	
	public String alteraCampoParaNomeClasse(String nomeDoCampo){
		String nome = "";
		if(nomeDoCampo == null || nomeDoCampo.isEmpty())
				return Configuracoes.NOME_CLASSE_SEM_BLUSA;
		
		if(nomeDoCampo.contains("Baby")){
			nome = regexNome(nomeDoCampo).replace("Babylook", "Feminina");
		}else{
			nome = regexNome(nomeDoCampo).replace(nomeDoCampo, "Masculina"+nomeDoCampo);
		}
		
		return nome;
	}
	
	public String validaSePossuiNome(){
		if(getNome().isEmpty()){
			return getNomeDonoPayPal() + " - " + getEmail();
		}
		return getNome() + " - " + getEmail();
	}
	
	public String geraHTML(){
		stringsHTML.append("</table>");
		return stringsHTML.toString();
	}
	
	public String geraHTMLSemNomes(){
		return this.semNomes.toString();
	}
	
	public String getNome(){
		return this.textoDoArquivoSeparado[COLUNA_NOME].replace("\"", "").concat(" ") + this.textoDoArquivoSeparado[COLUNA_SOBRE_NOME].replace("\"", "");
	}
	
	public String getNomeDonoPayPal(){
		return this.textoDoArquivoSeparado[COLUNA_DONO_PAY_PAL].replace("\"", "");
	}
	
	public String getEmail(){
		return this.textoDoArquivoSeparado[COLUNA_EMAIL].replace("\"", "").toString();
	}
	
	public String totalBlusas(){
		totalBlusas.append("Total de Camisas: ").append(this.BLUSA_TOTAL);
		totalBlusas.append("<br>");
		totalBlusas.append("Total Masculinas: ").append(totalMasculinas()).append("<br>");
		totalBlusas.append("Total P: ").append(BlusaMasculinaP.getTotal()).append("<br>");
		totalBlusas.append("Total M: ").append(BlusaMasculinaM.getTotal()).append("<br>");
		totalBlusas.append("Total G: ").append(BlusaMasculinaG.getTotal()).append("<br>");
		totalBlusas.append("Total GG: ").append(BlusaMasculinaGG.getTotal()).append("<br>");
		totalBlusas.append("Total Extra G: ").append(BlusaMasculinaEXG.getTotal()).append("<br>");
		totalBlusas.append("<br>");
		totalBlusas.append("Total Femininas: ").append(totalFemininas()).append("<br>");
		totalBlusas.append("Total P: ").append(BlusaFemininaP.getTotal()).append("<br>");
		totalBlusas.append("Total M: ").append(BlusaFemininaM.getTotal()).append("<br>");
		totalBlusas.append("Total G: ").append(BlusaFemininaG.getTotal()).append("<br>");
		totalBlusas.append("Total GG: ").append(BlusaFemininaGG.getTotal()).append("<br>");
		totalBlusas.append("Total Inscritos SEM CAMISA: ").append(BlusaSEMBlusa.getTotal()).append("<br>");
		return totalBlusas.toString();
	}
	
	private int totalMasculinas(){
		return (BlusaMasculinaP.getTotal() + 
		BlusaMasculinaM.getTotal() +
		BlusaMasculinaG.getTotal() +
		BlusaMasculinaGG.getTotal() +
		BlusaMasculinaEXG.getTotal());
	}
	
	private int totalFemininas(){
		return (BlusaFemininaP.getTotal() + 
				BlusaFemininaM.getTotal() +
				BlusaFemininaG.getTotal() +
				BlusaFemininaGG.getTotal());
	}
	
	public List<String> getSemNomes(){
		return this.semNomes;
	}
}
