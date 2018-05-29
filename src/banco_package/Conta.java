package banco_package;

public class Conta {
	private int numConta;
	private double saldo;
	private Cliente cliente;
	private static int proximoNumConta;
	//TODO lista movimentação
	public int getNumConta() {
		return numConta;
	}
	public double getSaldo() {
		return saldo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	

}
