package banco_package;
import java.util.ArrayList;

public class Conta {
	private int numConta;
	private double saldo;
	private Cliente cliente;
	private static int proximoNumConta = 1;
	private ArrayList<Movimentacao> listaMov = new ArrayList<Movimentacao>();
	
	Conta(Cliente cliente){
		this.saldo = 20;
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
	
}

