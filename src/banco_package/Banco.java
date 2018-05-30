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
	
	
}
