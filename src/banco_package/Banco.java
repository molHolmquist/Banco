package banco_package;

import java.util.ArrayList;

public class Banco {
	private String nomeBanco;
	private ArrayList<Cliente> listaClientes;
	private ArrayList<Conta> listaContas;
	
	Banco(String nomeBanco){
		this.nomeBanco = nomeBanco;
	}
	public void insereCliente(Cliente cliente) {
		listaClientes.add(cliente);
	}
	public void insereConta(Cliente cliente) {
		Conta c = new Conta(cliente);
		this.listaContas.add(c);
	}
	//TODO excluir cliente
	public void excluiConta(int numeroConta) {
		for(Conta c : this.listaContas) {
			if(c.getNumConta() == numeroConta) {
				this.listaContas.remove(c);
				break;
			}
		}
	}
	public void deposita(int numeroConta, double valor) {
		for(Conta c: this.listaContas) {
			if(c.getNumConta() == numeroConta) {
				c.creditaConta(valor, "Deposito");//TODO testar
			}
		}
	}
	
}
