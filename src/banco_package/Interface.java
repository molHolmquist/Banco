package banco_package;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Interface {
	
	public Banco banco;
	//TODO tornar banco private
	
	Interface(Banco b){
		banco = b;
	}
	public void menu() {
		
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
}
