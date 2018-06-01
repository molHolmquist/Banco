package banco_package;
import java.util.Scanner;

public class Interface {
	
	public Banco banco = new Banco("nome a ser extraido de um arquivo");
	
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
}
