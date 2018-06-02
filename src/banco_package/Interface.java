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
	public void excluiCliente() {
		//TODO testar
		System.out.println("Os clientes existentes são:");
		int i = 1;
		for(Cliente c : this.banco.getListaCliente()) {
			System.out.println(i+"-"+c.getNomeCliente()+c.getCpf_cnpj());
			i++;
		}
		Scanner scan = new Scanner(System.in);
		System.out.println("Qual você deseja exlcuir?");
		int n = scan.nextInt();
		if(n <= i || n > 0) {
			i = 1;
			for(Cliente c : this.banco.getListaCliente()) {
				if( i == n) {
					this.banco.excluiCliente(c.getNomeCliente());
				}
				i++;
			}
		}
		else
			System.out.println("Numero invalido");
		
	}
}
