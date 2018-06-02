package banco_package;
import java.util.Scanner;

public class Interface {
	
	public Banco banco = new Banco("nome a ser extraido de um arquivo");
	//TODO tornar banco private
	
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
		
	}
	public void criaConta() {
		System.out.println("Os clientes existentes são:");
		int i = 1;
		for(Cliente c : this.banco.getListaCliente()) { //Mostra todos os clientes
			System.out.println(i+"-"+c.getNomeCliente()+"-"+c.getCpf_cnpj());
			i++;
		}
		Scanner scan = new Scanner(System.in);
		System.out.println("Digite o número do cliente para o qual deseja criar a conta:");
		int n = scan.nextInt();
		if(n <= i && n > 0) {
			i = 1;
			for(Cliente c : this.banco.getListaCliente()) {
				if(i == n) {
					this.banco.criaConta(c);
					break;
				}
				i++;
			}
		}
		else
			System.out.println("Numero invalido");
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
			System.out.println("Nao existe uma conta com esse numero");
		}
	}
}
