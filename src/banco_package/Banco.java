package banco_package;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class Banco {
	private String nomeBanco;
	private ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
	private ArrayList<Conta> listaContas = new ArrayList<Conta>();
	
	Banco(String nomeBanco){
		this.nomeBanco = nomeBanco;
	}
	private Conta procuraConta(int numeroConta) { //procura uma conta com base
		for(Conta c : this.listaContas) {		  //no número dela.
			if(c.getNumConta() == numeroConta) {
				return c;
			}
		}
		return null;
	}
	public void insereCliente(Cliente cliente) {
		this.listaClientes.add(cliente);
	}
	public void criaConta(Cliente cliente) {
		Conta c = new Conta(cliente);
		this.listaContas.add(c);
	}
	//TODO excluir cliente	
//	public void excluiCliente(String cpfOuCnpj) {
//		boolean found = false;
//		Iterator<Cliente> itr = this.listaClientes.iterator();
//		while(itr.hasNext()) {
//			Cliente c = itr.next();
//			if(c.getCpf_cnpj().equals(cpfOuCnpj)) {
//				System.out.println("Rodou isso");
//				Iterator<Conta> itrcont = this.listaContas.iterator();
//				while(itrcont.hasNext()) {
//					Conta cont = itrcont.next();
//					if(c.equals(cont.getCliente())) {
//						System.out.println("Cliente possui conta");
//						found = false;
//						break;
//					}
//					else {found = true;}
//				}
//			}
//			if(found) {				
//				itr.remove();
//			}
//		}
//	}
	
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
	public void saca(int numeroConta, double valor) {
		for(Conta c:this.listaContas) {
			if(c.getNumConta() == numeroConta) {
				c.debitaConta(valor, "Saque");
			}
		}
	}
	public void transferencia(int numeroContaOrigem, int numeroContaDestino, double valor) {
		Conta origem = this.procuraConta(numeroContaOrigem);
		Conta destino = this.procuraConta(numeroContaDestino);
		if(origem.getSaldo() >= valor) {
			origem.debitaConta(valor, "Transferência para conta" + numeroContaDestino);
			destino.creditaConta(valor, "Transferencia da conta" + numeroContaOrigem);
		}
		else {System.out.println("A conta de origem não possui saldo suficiente");}
	}
	public void cobraTarifa() {
		for(Conta c: this.listaContas) {
			if(c.getSaldo() >= 15) {//So cobra 15 se a conta tiver saldo suficente
				c.debitaConta(15, "Cobrança de Tarifa");
			}
		}
	}
	public double saldoConta(int numeroConta) {
		Conta c = this.procuraConta(numeroConta);
		return c.getSaldo();
	}
	public ArrayList<Movimentacao> extratoConta(int numeroConta){
		Conta c = this.procuraConta(numeroConta);
		return c.extrato();
	}
	public ArrayList<Movimentacao> extratoConta(int numeroConta, GregorianCalendar dataInicial){
		Conta c = this.procuraConta(numeroConta);
		return c.extrato(dataInicial);
	}
	public ArrayList<Movimentacao> extratoConta(int numeroConta, GregorianCalendar dataInicial, GregorianCalendar dataFinal){
		Conta c = this.procuraConta(numeroConta);
		return c.extrato(dataInicial, dataFinal);
	}
	public ArrayList<Cliente> getListaCliente(){
		return this.listaClientes;
	}
	public ArrayList<Conta> getListaContas(){
		return this.listaContas;
	}
	
}
