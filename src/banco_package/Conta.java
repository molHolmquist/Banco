package banco_package;
import java.util.ArrayList;

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
	
	
}

