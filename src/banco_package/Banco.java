package banco_package;

import java.util.ArrayList;
import java.util.Iterator;

public class Banco {
	private String nomeBanco;
	private ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
	private ArrayList<Conta> listaContas = new ArrayList<Conta>();
	
	Banco(String nomeBanco){
		this.nomeBanco = nomeBanco;
	}
	public void insereCliente(Cliente cliente) {
		this.listaClientes.add(cliente);
	}
	public void insereConta(Cliente cliente) {
		Conta c = new Conta(cliente);
		this.listaContas.add(c);
	}
	//TODO excluir cliente	
	public void excluiCliente(String cpfOuCnpj) {
		boolean found = false;
		Iterator<Cliente> itr = this.listaClientes.iterator();
		while(itr.hasNext()) {
			Cliente c = itr.next();
			if(c.getCpf_cnpj().equals(cpfOuCnpj)) {
				System.out.println("Rodou isso");
				Iterator<Conta> itrcont = this.listaContas.iterator();
				while(itrcont.hasNext()) {
					Conta cont = itrcont.next();
					if(c.equals(cont.getCliente())) {
						System.out.println("Cliente possui conta");
						found = false;
						break;
					}
					else {found = true;}
				}
			}
			if(found) {				
				itr.remove();
			}
		}
	}
	
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
				c.creditaConta(valor, "Deposito");//TODO t
			}
		}
	}
	
	
	public ArrayList<Cliente> getListaCliente(){
		return this.listaClientes;
	}
	public ArrayList<Conta> getListaContas(){
		return this.listaContas;
	}
	
}
