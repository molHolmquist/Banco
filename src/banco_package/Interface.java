package banco_package;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import java.util.Scanner;

public class Interface {
	
	public Banco banco;
	//TODO tornar banco private
	
	Interface(Banco b){
		banco = b;
	}
	public void cadastraCliente() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Insira o nome do Cliente:");
		String nome = scan.nextLine();
		System.out.println("Insira o cpf ou o cnpj do Cliente:");
		String c = scan.nextLine();
		System.out.println("Insira o endereco do Cliente:");
		String end = scan.nextLine();
		System.out.println("Insira o numero de telefone do Cliente:");
		String f = scan.nextLine();
		Cliente cliente = new Cliente(nome, c, end, f);
		this.banco.insereCliente(cliente);
		menu();
	}
	public void excluiCliente() {
			System.out.println("Os clientes existentes são:");
			int i = 1;
			for(Cliente c : this.banco.getListaCliente()) { //Mostra todos os clientes
				System.out.println(i+"-"+c.getNomeCliente()+"-"+c.getCpf_cnpj());
				i++;
			}
			Scanner scan = new Scanner(System.in);
			System.out.println("Qual você deseja exlcuir?");
			int n = scan.nextInt();
			if(n <= i && n > 0) {
				i = 1;
				for(Cliente c : this.banco.getListaCliente()) {
					if(i == n) {
						this.banco.excluiCliente(c.getCpf_cnpj());
						break;
					}
					i++;
				}
			}
			else
				System.out.println("Numero invalido");
		menu();
	}
	public void criaConta() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Digite o número do CPF ou CNPJ do cliente para o qual deseja criar a conta:");
		String n = scan.nextLine();
		boolean notFound = true;
			for(Cliente c : this.banco.getListaCliente()) {
				if(n == c.getCpf_cnpj()) {
					this.banco.criaConta(c);
					notFound = false; 
					break;
				}
			}
			if(notFound) {
				System.out.println("O CPF ou CNPJ dado não pertece a nenhum cliente");
			}
			menu();
	}
	public void excluiConta() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Digite o número da conta a ser excluída:");
		int numeroConta = scan.nextInt();
		boolean notFound = true;
		for(Conta c : this.banco.getListaContas()) {
			if(c.getNumConta() == numeroConta) {
				this.banco.excluiConta(numeroConta);
				notFound = false;
				break;
			}
		}
		if(notFound) {
			System.out.println("Não existe uma conta com esse numero");
		}
		menu();
	}
	public void cobraTarifa() {
		this.banco.cobraTarifa();
		menu();
	}
	public void cobraCPMF() {
		this.cobraCPMF();
		menu();
	}
	public void obtemSaldo() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Digite o número da conta que deseja saber o saldo:");
		int numeroConta = scan.nextInt();
		System.out.println("O saldo dessa conta é"+this.banco.saldoConta(numeroConta));
		menu();
	}
	private void imprimeExtrato(ArrayList<Movimentacao> extrato) {
		for(Movimentacao mov : extrato) {
			System.out.println("Data:"+mov.getDataMov().getTime());
			System.out.println("Descrição:"+mov.getDescricao());
			System.out.println("Debito ou crédito:"+mov.getDebitoCredito());
			System.out.println("Valor:"+mov.getValor());
		}
	}
	public void obtemExtrato() {
			Scanner scan = new Scanner(System.in);
			System.out.println("Digite o número da conta:");
			int numeroConta = scan.nextInt();
			System.out.println("Digite o número equivalente ao extrato que desjea:");
			System.out.println("1- Extrato do mês");
			System.out.println("2- Extrato a partir de uma data");
			System.out.println("3- Extrato de um período específico");
			int n = scan.nextInt();
			String dia = null; String mes = null; String ano = null;
			GregorianCalendar dataInicial = null;
			String Data = new String();
			switch (n) {
			case 1 : imprimeExtrato(this.banco.extratoConta(numeroConta));
				break;
			case 2: System.out.println("Digite a data inicial no formato d/m/aaaa:");
				Data = scan.nextLine();
				dia = Data.substring(0, Data.indexOf("/"));
				mes = Data.substring(Data.indexOf("/")+1, Data.indexOf("/",Data.indexOf("/")+1));
				ano = Data.substring(1+Data.indexOf("/",Data.indexOf("/")+1), Data.length());
				dataInicial = new GregorianCalendar(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia));
				this.imprimeExtrato(this.banco.extratoConta(numeroConta, dataInicial));
				break;
			case 3: System.out.println("Digite a data inicial no formato d/m/aaaa:");
				Data = scan.nextLine();
				dia = Data.substring(0, Data.indexOf("/"));
				mes = Data.substring(Data.indexOf("/")+1, Data.indexOf("/",Data.indexOf("/")+1));
				ano = Data.substring(1+Data.indexOf("/",Data.indexOf("/")+1), Data.length());
				dataInicial = new GregorianCalendar(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia));
				System.out.println("Digite a data final no formato d/m/aaaa:");
				Data = scan.nextLine();
				dia = Data.substring(0, Data.indexOf("/"));
				mes = Data.substring(Data.indexOf("/")+1, Data.indexOf("/",Data.indexOf("/")+1));
				ano = Data.substring(1+Data.indexOf("/",Data.indexOf("/")+1), Data.length());
				GregorianCalendar dataFinal = new GregorianCalendar(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia));
				this.imprimeExtrato(this.banco.extratoConta(numeroConta, dataInicial, dataFinal));
				break;
			default: System.out.println("Valor inválido");
			break;
				} 		
		menu();
	}
	public void efetuarDeposito() {
		
		Scanner scan = null;
	    scan = new Scanner(System.in);
	    System.out.println("Insira o número da conta:");
	    String num_Conta = scan.nextLine();
	    System.out.println("Insira o valor que se deseja depositar:");
	    String valor = scan.nextLine();
	    banco.depositaConta(Integer.parseInt(num_Conta), Double.parseDouble(valor));
	    System.out.println("Depósito realizado.");

		menu();
		
	}
	public void efetuarSaque() {
		
		Scanner scan = null;
	    scan = new Scanner(System.in);
	    System.out.println("Insira o número da conta:");
	    String num_Conta = scan.nextLine();
	    System.out.println("Insira o valor que se deseja sacar:");
	    String valor = scan.nextLine();
	    banco.saqueConta(Integer.parseInt(num_Conta), Double.parseDouble(valor));
	    System.out.println("Saque realizado.");
		menu();
	}
	
	public void efetuarTransferencia() {
		
		Scanner scan = null;
	    scan = new Scanner(System.in);
	    System.out.println("Insira o número da conta de origem para transferência:");
	    String num_Conta1 = scan.nextLine();
	    System.out.println("Insira o número da conta destino:");
	    String num_Conta2 = scan.nextLine();
	    System.out.println("Insira o valor da transferência:");
	    String valor = scan.nextLine();
	    banco.transferencia(Integer.parseInt(num_Conta1), Integer.parseInt(num_Conta2), Double.parseDouble(valor));
	    System.out.println("Transferência realizada.");
		menu();
		
	}
	
	public void listarClientes() {
		
		ArrayList<Cliente> listaClientes = banco.getListaCliente();
		
		System.out.println("LISTA DE CLIENTES");
		
		for(Cliente c: listaClientes) {
			String nome = c.getNomeCliente();
			String cpf_cnpj = c.getCpf_cnpj();
			String endereco = c.getEndereco();
			String fone = c.getFone();
			System.out.println("-----------------------------");
			System.out.println(nome);
			System.out.println(cpf_cnpj);
			System.out.println(endereco);
			System.out.println(fone);
		}
		
		menu();
		
	}
	
	public void listarContas() {
		
		ArrayList<Conta> listaContas = banco.getListaContas();
		
		System.out.println("LISTA DE CONTAS");
		
		for(Conta c: listaContas) {
			
			int numConta = c.getNumConta();
			double saldo = c.getSaldo();
			System.out.println("-----------------------------");
			System.out.println("Número de conta: " + numConta);
			System.out.println("Saldo: " + saldo);

		}
		
		menu();
		
	}
	
	public void menu() {
		
		Scanner scan = null;
		try {
			scan = new Scanner(System.in);
			if(menuAberto != 0) {
				System.out.println();
			    System.out.println("Aperte enter para voltar ao menu");
			    String a = scan.nextLine();
			}
		    menuAberto++;
			jumpSpace();
			System.out.println("----------MENU----------");
			System.out.println("1: Cadastrar novo cliente");
			System.out.println("2: Criar nova conta");
			System.out.println("3: Excluir um cliente");
			System.out.println("4: Excluir uma conta");
			System.out.println("5: Efetuar depósito");
			System.out.println("6: Efetuar saque");
			System.out.println("7: Efetuar transferência entre contas");
			System.out.println("8: Cobrar tarifa");
			System.out.println("9: Cobrar CPMF");
			System.out.println("10: Obter saldo");
			System.out.println("11: Obter extrato");
			System.out.println("12: Listar clientes");
			System.out.println("13: Listar contas");
			System.out.println("q : Sair do programa");


		    String s = scan.nextLine();
		    switch(s) {
		    case "1":
		    	cadastraCliente();
		    	break;
		    case "2":
		    	criaConta();
		    	break;
		    case "3":
		    	excluiCliente();
		    	break;
		    case "4":
		    	excluiConta();
		    	break;
		    case "5":
		    	efetuarDeposito();
		    	break;
		    case "6":
		    	efetuarSaque();
		    	break;
		    case "7":
		    	efetuarTransferencia();
		    	break;
		    case "8":
		    	cobraTarifa();
		    	break;
		    case "9":
		    	cobraCPMF();
		    	break;
		    case "10":
		    	obtemSaldo();
		    	break;
		    case "11":
		    	obtemExtrato();
		    	break;
		    case "12":
		    	listarClientes();
		    	break;
		    case "13":
		    	listarContas();
		    	break;
		    case "q":
		    	//Apenas sai do menu
		    	banco.gravarDadosArquivo();
		    	break;
		    default:
		    	System.out.println("Entrada inválida. Digite um valor entre 1 e 13, ou então saia do programa e grave os dados com q.");
		    	menu();
		    	//throw new IllegalArgumentException("Função inválida. Digite um valor entre 1 e 13, ou então saia do programa com q.");
		    
		    }
		}
		finally {
		    if(scan!=null)
		        scan.close();
		}
		
	}
	
	private void jumpSpace() {
		for (int i = 0; i < 3; ++i)  
		       System.out.println();
	}
	
	private static int menuAberto = 0;



}
