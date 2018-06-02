package banco_package;

public class MainInterface {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Banco bradesco = new Banco("Bradesco");
		bradesco.leituraDadosArquivo();
	
		Interface banquinho = new Interface(bradesco);
		banquinho.menu();
//		banquinho.cadastraCliente();
//		banquinho.cadastraCliente();
//		banquinho.criaConta();
//		for(Cliente c: banquinho.banco.getListaCliente()) {
//			System.out.println("nome dele �:"+c.getNomeCliente());
//			System.out.println("cpf dele �:"+c.getCpf_cnpj());
//			System.out.println("endereco dele �:"+c.getEndereco());
//			System.out.println("telegone dele �:"+c.getFone());
//		}
		//banquinho.banco.gravarDadosArquivo("bancotop.txt");
		
	}

}
