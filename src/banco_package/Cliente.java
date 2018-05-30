package banco_package;
import java.util.*;

public class Cliente {

	public static void main(String[] args) {
		
		Cliente juse = new Cliente("Juse","07320176610","Rua Jose Afonso","3241-5264");
		Conta contaJuse = new Conta(juse);
		contaJuse.creditarConta(50, "Valor recebido por loucura do juse.");
		contaJuse.creditarConta(300, "Vendeu a bicicleta.");
		contaJuse.creditarConta(5000, "Ganhou na loteria.");
		contaJuse.creditarConta(200, "Achou no chao.");
		
		Cliente arnaldo = new Cliente("Arnaldo","09087987709","Rua Jose Afonso","3241-5264");
		Conta contaArnaldo = new Conta(arnaldo);
		contaArnaldo.creditarConta(100,  "Restaurante Boi dos Vales");
		contaArnaldo.creditarConta(50,  "Restaurante Boi dos Vales");
		contaArnaldo.creditarConta(20,  "Arroz");
		contaArnaldo.creditarConta(300,  "Batata");
		//GregorianCalendar data = new GregorianCalendar();
		GregorianCalendar data = new GregorianCalendar(2018, 3, 25);
		
		System.out.println(arnaldo.getCpf_cnpj());
		
		ArrayList<Movimentacao> lista_segredos_arnaldo = contaArnaldo.extrato(data);
		
		System.out.println(lista_segredos_arnaldo.size());
		for(Movimentacao m: lista_segredos_arnaldo) {
			
			System.out.println(m.getDescricao());
			System.out.println(m.getDataMov().toZonedDateTime());
			
		}
		
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