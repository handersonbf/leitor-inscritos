package main;

public class BlusaFemininaG implements Blusa {
	private static Integer total = 0;
	public void contaBlusas() {
		total++;
	}
	@Override
	public String toString() {
		return "<font color='#FF00FF'><strong>G-Feminina</strong></font>";
	}
	
	public static Integer getTotal() {
		return BlusaFemininaG.total;
	}

}
