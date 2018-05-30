package banco_package;

public class Cliente {

	//Adicionando feature 1
	public static void main(String[] args) {
		
		Cliente juse = new Cliente("Juse","07320176610","Rua Jose Afonso","3241-5264");
//		System.out.println(juse.getNomeCliente());
//		System.out.println(juse.cpf_cnpj);
//		System.out.println(juse.fone);
		Conta contaJuse = new Conta(juse);
		contaJuse.debitaConta(22, "Compra de bala");
		System.out.println(contaJuse.getSaldo());

	}
	
	private String nomeCliente;
	private String cpf_cnpj;
	private String endereco;
	private String fone;
	
	Cliente(String nome, String c, String end, String f){
		nomeCliente = new String(nome);
		cpf_cnpj = new String(c);
		endereco = new String(end);
		fone = new String(f);
		
	}
	
	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}
	
	
	
	
}