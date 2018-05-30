package banco_package;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class Cliente {

	//Adicionando feature 1
	public static void main(String[] args) {
		
		Cliente juse = new Cliente("Juse","07320176610","Rua Jose Afonso","3241-5264");
		Conta contaJuse = new Conta(juse);
//		contaJuse.debitaConta(2, "Compra dey bala");//Operacoes de teste
//		contaJuse.debitaConta(1, "Compra de bala");
//		contaJuse.debitaConta(1.56, "Compra de livro");
//		contaJuse.debitaConta(7, "Compra de chiclete");
//		GregorianCalendar dataFinal = new GregorianCalendar(2018, 4, 28);
//		GregorianCalendar dataInicial = new GregorianCalendar(2018, 1, 1);
//		ArrayList<Movimentacao> juseMov = contaJuse.extrato(dataInicial, dataFinal);
//		for(Movimentacao Mov: juseMov) {
//			SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
//		    fmt.setCalendar(Mov.getDataMov());
//		    String dateFormatted = fmt.format(Mov.getDataMov().getTime());
//			System.out.println(Mov.getDescricao() + "-mes:"+ " " + dateFormatted);
//			System.out.println(Mov.getDescricao()+"-mes:"+Mov.getDataMov().toZonedDateTime());
//		}
		//System.out.println(contaJuse.getSaldo());

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