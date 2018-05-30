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
	public void creditarConta(double valorCreditado, String descricaoMovimentacao) {
		
		Movimentacao nova_movimentacao = new Movimentacao(descricaoMovimentacao, 'C', valorCreditado);
		listaMov.add(nova_movimentacao);
		saldo += valorCreditado;
	}
	public ArrayList<Movimentacao> extrato(GregorianCalendar dataInicial){
		
		Iterator<Movimentacao> Lista_movimentacao_iterator = listaMov.iterator();
		
		ArrayList<Movimentacao> listaRetorno = new ArrayList<Movimentacao>();
		
		while(Lista_movimentacao_iterator.hasNext()) {
			Movimentacao m = Lista_movimentacao_iterator.next();
			
			// Quanto ao método compareTo aplicado na data do interador específico m
			// que está passando por toda a lista, sabe-se:
			
			// Retorna 0 se na mesma data que dataInicial
			// Retorna número negativo se no futuro em relação a dataInicial
			// Retorna número positivo se no passado em relação a dataInicial
			
			if(m.getDataMov().compareTo(dataInicial)<=0) {
				listaRetorno.add(m);
			}
			
		}
		return listaRetorno;
	}
	
	
}

