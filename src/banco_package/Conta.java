package banco_package;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class Conta {
	private int numConta;
	private double saldo;
	private Cliente cliente;
	private static int proximoNumConta = 1;
	private ArrayList<Movimentacao> listaMov = new ArrayList<Movimentacao>();
	
	Conta(Cliente cliente){
		this.saldo = 0;
		this.cliente = cliente;
		this.numConta = proximoNumConta;
		proximoNumConta++;
	}
	public int getNumConta() {
		return numConta;
	}
	public double getSaldo() {
		return saldo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void debitaConta(double valor, String descricao) {
		Movimentacao Mov = new Movimentacao(descricao, 'D', valor);
		double novo_saldo = this.saldo - valor;
		if(novo_saldo > 0) {
			this.saldo = novo_saldo;
			this.listaMov.add(Mov);
		}
		else {
			System.out.println("Movimentacao invalida");
		}
	}
	
	
	public ArrayList<Movimentacao> extrato(GregorianCalendar dataInicial, GregorianCalendar dataFinal){
			
			Iterator<Movimentacao> Lista_movimentacao_iterator = listaMov.iterator();
			
			ArrayList<Movimentacao> listaRetorno = new ArrayList<Movimentacao>();
			
			while(Lista_movimentacao_iterator.hasNext()) {
				Movimentacao m = Lista_movimentacao_iterator.next();
				
				// Quanto ao m�todo compareTo aplicado na data do interador espec�fico m
				// que est� passando por toda a lista, sabe-se:
				
				// Retorna 0 se na mesma data que dataInicial
				// Retorna n�mero negativo se no futuro em rela��o a dataInicial
				// Retorna n�mero positivo se no passado em rela��o a dataInicial
				
				if( (m.getDataMov().compareTo(dataInicial)>=0) && (m.getDataMov().compareTo(dataFinal)<=0)) {
					listaRetorno.add(m);
				}
				
			}
			return listaRetorno;
		}
	public ArrayList<Movimentacao> extrato(){
		GregorianCalendar calendar = new GregorianCalendar();
		int month = calendar.get(calendar.MONTH);
		ArrayList<Movimentacao> extratoMes = new ArrayList<Movimentacao>();
		for(Movimentacao Mov:listaMov) {
			if(month == Mov.getDataMov().get(Mov.getDataMov().MONTH)) {
				extratoMes.add(Mov);
			}
		}
		return extratoMes;
	}
	
}

