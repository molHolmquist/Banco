package banco_package;

import java.util.GregorianCalendar;

public class Movimentacao {

	private GregorianCalendar dataMov;
	private String descricao;
	private char debitoCredito;
	private double valor;
	private static int mes = 2;
	
	
	Movimentacao(String desc, char debCre, double val){
		
		dataMov = new GregorianCalendar(2018, mes, 28);
		descricao = new String(desc);
		debitoCredito = debCre;
		valor = val;
		mes++;
		
	}


	public GregorianCalendar getDataMov() {
		return dataMov;
	}


	public String getDescricao() {
		return descricao;
	}


	public char getDebitoCredito() {
		return debitoCredito;
	}


	public double getValor() {
		return valor;
	}
	
}
