package banco_package;

import java.util.*;


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
	public static int getNumeroDeContas() {
		return proximoNumConta;
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
	public void creditaConta(double valorCreditado, String descricaoMovimentacao) {
		
		Movimentacao nova_movimentacao = new Movimentacao(descricaoMovimentacao, 'C', valorCreditado);
		listaMov.add(nova_movimentacao);
		saldo += valorCreditado;
	}
	public ArrayList<Movimentacao> extrato(GregorianCalendar dataInicial){
		
		ArrayList<Movimentacao> listaRetorno = new ArrayList<Movimentacao>();
		
		for(Movimentacao m: listaMov) {
			//compareTo: positivo se no futuro em rela��o a dataInicial.
			if(m.getDataMov().compareTo(dataInicial)>=0) {
				listaRetorno.add(m);
			}
		}
		
		return listaRetorno;
	}
	
	public ArrayList<Movimentacao> extrato(GregorianCalendar dataInicial, GregorianCalendar dataFinal){
			
			ArrayList<Movimentacao> listaRetorno = new ArrayList<Movimentacao>();
			
			for(Movimentacao m: listaMov) {
				//compareTo: positivo se no futuro em rela��o a dataInicial.
				if( (m.getDataMov().compareTo(dataInicial)>=0) && (m.getDataMov().compareTo(dataFinal)<=0)) {
					listaRetorno.add(m);
				}
			}
			
			return listaRetorno;
	}
	
	public ArrayList<Movimentacao> extrato(){
		
		GregorianCalendar calendar = new GregorianCalendar();
		int month = calendar.get(Calendar.MONTH); //mes atual

		ArrayList<Movimentacao> extratoMes = new ArrayList<Movimentacao>();
		
		for(Movimentacao Mov:listaMov) {
		
			GregorianCalendar cal = Mov.getDataMov();
			int MovMonth = cal.get(Calendar.MONTH); // mes da movimentacao Mov
			
			if(month == MovMonth) {
				extratoMes.add(Mov);
			}
		}
		return extratoMes;
	}
	

	
}

