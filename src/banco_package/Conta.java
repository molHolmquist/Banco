package banco_package;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class Conta {
	private int numConta;
	private double saldo;
	private Cliente cliente;
	private static int proximoNumConta = 1;
	private ArrayList<Movimentacao> listaMov = new ArrayList<Movimentacao>();
	
	Conta(Cliente cliente){
		this.saldo = 20; //TODO zerar isso
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
		this.listaMov.add(Mov);
		double novo_saldo = this.saldo - valor;
		if(novo_saldo > 0) {
			this.saldo = novo_saldo;
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
				
				// Quanto ao método compareTo aplicado na data do interador específico m
				// que está passando por toda a lista, sabe-se:
				
				// Retorna 0 se na mesma data que dataInicial
				// Retorna número negativo se no futuro em relação a dataInicial
				// Retorna número positivo se no passado em relação a dataInicial
				
				if( (m.getDataMov().compareTo(dataInicial)>=0) && (m.getDataMov().compareTo(dataFinal)<=0)) {
					listaRetorno.add(m);
				}
				
			}
			return listaRetorno;
		}
	
}

