package banco_package;

import static java.nio.file.StandardOpenOption.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.io.*;
import java.util.*;

public class Banco {
	private String nomeBanco;
	private ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
	private ArrayList<Conta> listaContas = new ArrayList<Conta>();
	
	Banco(String nomeBanco){
		this.nomeBanco = nomeBanco;
	}
	private Conta procuraConta(int numeroConta) { //procura uma conta com base
		for(Conta c : this.listaContas) {		  //no n�mero dela.
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
	public void excluiCliente(String cpfOuCnpj) {
		
		boolean foundConta = false;
		
		for(Conta contaIterator : listaContas) {
			
			Cliente c = contaIterator.getCliente();			
			String cpfOuCnpj_Cliente = c.getCpf_cnpj();
			
			if(cpfOuCnpj_Cliente.equals(cpfOuCnpj)) {	
				//Conta de cliente que se deseja remover encontrada.
				foundConta = true;
				//Cliente n�o pode ser exclu�do com conta no banco.
				System.out.println("Cliente possui uma conta no banco e por isso n�o ser� exclu�do.");
				break;
			}
			
		}
		
		// Se n�o achou a conta, deletar cliente.
		if(!foundConta) {
			
			boolean foundCliente = false;
			for(Cliente c : listaClientes) {
				
				String cpfOuCnpj_Cliente = c.getCpf_cnpj();
				
				if(cpfOuCnpj_Cliente.equals(cpfOuCnpj)) {
					
					listaClientes.remove(c);
					foundCliente = true;
					break;
				}
			}
			// Se n�o achou o cliente, avisar usu�rio que esse cliente
			// n�o faz parte deste banco
			if(!foundCliente) {
				System.out.println("Cliente n�o faz parte deste banco, logo n�o faz sentido exclu�-lo.");
				
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
	public void depositaConta(int numeroConta, double valor) {
		for(Conta c: this.listaContas) {
			if(c.getNumConta() == numeroConta) {
				c.creditaConta(valor, "Deposito");//TODO t
			}
		}
	}
	public void saqueConta(int numeroConta, double valor) {
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
			origem.debitaConta(valor, "Transfer�ncia para conta " + numeroContaDestino);
			destino.creditaConta(valor, "Transferencia da conta " + numeroContaOrigem);
		}
		else {System.out.println("A conta de origem n�o possui saldo suficiente");}
	}
	public void cobraTarifa() {
		for(Conta c: this.listaContas) {
			if(c.getSaldo() >= 15) {//So cobra 15 se a conta tiver saldo suficente
				c.debitaConta(15, "Cobran�a de Tarifa");
			}
		}
	}
	public void cobraCPMF() {
		GregorianCalendar hoje = new GregorianCalendar();
		hoje.add((GregorianCalendar.DAY_OF_MONTH), -1);
		GregorianCalendar semanaPassada = new GregorianCalendar();
		semanaPassada.add((GregorianCalendar.DAY_OF_MONTH), -7);
		

		for(Conta c : this.listaContas) {
			double CPMF = 0;
			for(Movimentacao m : c.extrato(semanaPassada, hoje)){
				if(m.getDebitoCredito() == 'D') {
					CPMF = CPMF + (0.0038*m.getValor());
				}
			}
			if(CPMF > 0)
				c.debitaConta(CPMF, "Cobran�a de CPMF");			
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
	public String getNomeBanco() {
		//Adicionado para que o NomeBanco n�o seja perdido depois
		//que seu valor for atribuido no construtor.
		return nomeBanco;
	}
	public void gravarDadosArquivo() {
		
		
		List<String> lines = new ArrayList<>();
		//Impress�o de contas
		lines.add("-----------------------------------------");
		lines.add("---------------CONTAS--------------------");	
		lines.add("-----------------------------------------");	
		
		
		lines.add("TOTAL DE CONTAS FEITAS = " + Conta.getNumeroDeContas());
		
		//Lista de clientes sem conta, que inicialmente � s� uma c�pia de listaClientes
		ArrayList<Cliente> listaClientesSemConta = new ArrayList<Cliente>(listaClientes);
		
		for(Conta contaI : listaContas) {
			
			//Cliente auxiliar
			Cliente c = contaI.getCliente();
			
			//Impress�o de n�mero da conta
			int numConta = contaI.getNumConta();
			lines.add(String.valueOf(numConta));
			
			//Impress�o do saldo
			lines.add(String.valueOf(contaI.getSaldo()));
			
			//Como sei que esse cliente tem uma conta, retiro ele da lista auxiliar
			listaClientesSemConta.remove(c);
			
			//////////////Impress�o informa��es de cliente
			lines.add(c.getNomeCliente()); //Nome do cliente
			lines.add(c.getCpf_cnpj()); //CPF ou CNPJ
			lines.add(c.getEndereco()); //Endere�o do cliente
			lines.add(c.getFone()); //Telefone do cliente
			
			lines.add("------------------------------");
		}
		
		//Impress�o de clientes sem conta
		lines.add("-----------------------------------------");
		lines.add("CLIENTES SEM CONTA");
		lines.add("-----------------------------------------");
		for(Cliente c : listaClientesSemConta) {
			
			lines.add(c.getNomeCliente()); //Nome do cliente
			lines.add(c.getCpf_cnpj()); //CPF ou CNPJ
			lines.add(c.getEndereco()); //Endere�o do cliente
			lines.add(c.getFone()); //Telefone do cliente
			
			lines.add("-----------------------------------------");
			
		}
		
		Charset utf8 = StandardCharsets.UTF_8;
		try {
			Files.write(Paths.get("DadosBanco.txt"), lines, utf8);
		    //Files.write(Paths.get("file6.txt"), lines, utf8,
		    //        StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		    
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
	}
	public void leituraDadosArquivo() {
		
		
	}
}
