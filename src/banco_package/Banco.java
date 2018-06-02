package banco_package;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
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
		for(Conta c : this.listaContas) {		  //no número dela.
			if(c.getNumConta() == numeroConta) {
				return c;
			}
		}
		return null;
	}
	private void carregarConta(Cliente cliente, String saldo, ArrayList<Movimentacao> lMov) { 
		// Método exclusivo para o processo de leitura
		Conta c = new Conta(cliente,saldo, lMov);
		listaContas.add(c);
		
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
				//Cliente não pode ser excluído com conta no banco.
				System.out.println("Cliente possui uma conta no banco e por isso não será excluído.");
				break;
			}
			
		}
		
		// Se não achou a conta, deletar cliente.
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
			// Se não achou o cliente, avisar usuário que esse cliente
			// não faz parte deste banco
			if(!foundCliente) {
				System.out.println("Cliente não faz parte deste banco, logo não faz sentido excluí-lo.");
				
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
			origem.debitaConta(valor, "Transferência para conta " + numeroContaDestino);
			destino.creditaConta(valor, "Transferencia da conta " + numeroContaOrigem);
		}
		else {System.out.println("A conta de origem não possui saldo suficiente");}
	}
	public void cobraTarifa() {
		for(Conta c: this.listaContas) {
			if(c.getSaldo() >= 15) {//So cobra 15 se a conta tiver saldo suficente
				c.debitaConta(15, "Cobrança de Tarifa");
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
				c.debitaConta(CPMF, "Cobrança de CPMF");			
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
		//Adicionado para que o NomeBanco não seja perdido depois
		//que seu valor for atribuido no construtor.
		return nomeBanco;
	}
	public void gravarDadosArquivo() {
		
		String nomeArquivo = new String(nomeBanco + ".txt");
		
		List<String> lines = new ArrayList<>();
		//Impressão de contas
		lines.add("------------CONTAS BANCÁRIAS-------------");	
		lines.add("-----------------------------------------");	
		lines.add("TOTAL DE CONTAS FEITAS");
		lines.add(String.valueOf(Conta.getNumeroDeContas()-1));
		lines.add("-----------------------------------------");
		
		//Lista de clientes sem conta, que inicialmente é só uma cópia de listaClientes
		ArrayList<Cliente> listaClientesSemConta = new ArrayList<Cliente>(listaClientes);
		
		for(Conta contaI : listaContas) {
			
			//Cliente auxiliar
			Cliente c = contaI.getCliente();
			
			//Impressão de número da conta
			int numConta = contaI.getNumConta();
			lines.add(String.valueOf(numConta));
			

			
			
			//Como sei que esse cliente tem uma conta, retiro ele da lista auxiliar
			listaClientesSemConta.remove(c);
			
			//////////////Impressão informações de cliente/////////////////////
			lines.add(c.getNomeCliente()); //Nome do cliente
			//------------------------------------
			//Impressão do saldo - NAO INCLUSO EM INFORMACOES DE CLIENTE
			//EXCEÇÃO PARA MANTER ORGANIZAÇÃO NO ARQUIVO TEXTO
			lines.add(String.valueOf(contaI.getSaldo()));
			//------------------------------------
			lines.add(c.getCpf_cnpj()); //CPF ou CNPJ
			lines.add(c.getEndereco()); //Endereço do cliente
			lines.add(c.getFone()); //Telefone do clientes
			///////////////////////////////////////////////////////////////////
			
			ArrayList<Movimentacao> listaMovCliente = contaI.getListaMov();
			lines.add("");
			lines.add("MOVIMENTACOES");
			lines.add("");
			for(Movimentacao m : listaMovCliente) {
				lines.add("=====");
				GregorianCalendar calendar = m.getDataMov();
				int ano = calendar.get(Calendar.YEAR);
				int mes = calendar.get(Calendar.MONTH) + 1;
				int dia = calendar.get(Calendar.DAY_OF_MONTH);
				lines.add(dia + "/" + mes + "/" + ano); // Data de movimentacao
				lines.add(m.getDescricao()); //Descrição da movimentacao
				lines.add(String.valueOf(m.getDebitoCredito())); // Credito ou debito
				lines.add(String.valueOf(m.getValor())); //Valor da movimentacao
			}
			
			lines.add("------------------------------");
		}
		
		//Impressão de clientes sem conta
		lines.add("-----------------------------------------");
		lines.add("CLIENTES SEM CONTA");
		lines.add("-----------------------------------------");
		for(Cliente c : listaClientesSemConta) {
			
			lines.add(c.getNomeCliente()); //Nome do cliente
			lines.add(c.getCpf_cnpj()); //CPF ou CNPJ
			lines.add(c.getEndereco()); //Endereço do cliente
			lines.add(c.getFone()); //Telefone do cliente
			
			lines.add("-----------------------------------------");
			
		}
		
		Charset utf8 = StandardCharsets.UTF_8;
		try {
			Files.write(Paths.get(nomeArquivo), lines, utf8);
		    //Files.write(Paths.get("file6.txt"), lines, utf8,
		    //        StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		    
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
	}
	public void leituraDadosArquivo() {
		
		String nomeArquivo = new String(nomeBanco + ".txt");
		
		Path path = Paths.get(nomeArquivo);
		String FILENAME = String.valueOf(path);
		BufferedReader br = null;
		FileReader fr = null;

		try {

			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;

			int lineCounter = 0;

			//Parametros de conta e cliente usados
			String nome = new String();
			String cpfOucnpj = new String();
			String endereco = new String();
			String fone = new String();
			String saldo = new String();
			
			
			boolean leituraPrimeirasLinhas = false;
			boolean leituraContasCompleta = false;

			
			while(((sCurrentLine = br.readLine()) != null) && !leituraPrimeirasLinhas) {
				
				lineCounter++;
				if(lineCounter == 5) {
					//Primeiras linhas lidas.
					leituraPrimeirasLinhas = true;
				}
			}
			if(sCurrentLine.startsWith("-")) {
				
				//Significa que nao tem nenhuma conta no banco
				leituraContasCompleta = true;
			}
			else { // Há conta no banco.
				//Leitura de Contas
				boolean clienteComMovimentacoes = false;
				boolean leituraInformacoesContaIndividual = false;
				
				 //Enquanto leitura das contas nao for terminada, continuar nesse loop
				while(!leituraContasCompleta) {
					
					/////Garantir que condicoes depois do loop voltarao ao normal
					int linesConta = 1;
					/////numa nova leitura de conta
					leituraInformacoesContaIndividual = false;
					clienteComMovimentacoes = false;
					/////
					
					//As informações da conta individual será feita nesse loop
					while ((sCurrentLine = br.readLine()) != null && !leituraInformacoesContaIndividual) {
	
							linesConta++;
							
							switch(linesConta) {
								case 2:
									//grava nome
									nome = sCurrentLine;
									break;
								case 3:
									//grava saldo
									saldo = sCurrentLine;
									break;
								case 4:
									cpfOucnpj = sCurrentLine;
									break;
								case 5:
									endereco = sCurrentLine;
									break;
								case 6:
									fone = sCurrentLine;
									break;
								case 7:
									//espaço em branco nessa linha
									break;
								case 8:
									// escrito MOVIMENTACOES no arquivo
									break;
								case 9:
									//espaço em branco nessa linha novamente
									break;
								case 10:
									//Quando começa movimentações, se movimentações existe
									//o primeiro caracter é =
									if(sCurrentLine.startsWith("=")) {
										clienteComMovimentacoes = true;
									}else {
										clienteComMovimentacoes = false;
									}
									leituraInformacoesContaIndividual = true;
									//Terminado de ler a conta
									break;
								default:
									linesConta = 0;
							}
					}//Terminado de ler informações da conta individual (fim de loop de leitura de informações de Conta)

					
					//Leitura de movimentacoes caso cliente tenha movimentacoes
					ArrayList<Movimentacao> listaMovCliente = new ArrayList<Movimentacao>();
					if(clienteComMovimentacoes) {
						
						int contadorMovimentacoes = 0;
						boolean leituraMovimentacoesCompleta = false;
						
						//Parametros da movimentacao
						GregorianCalendar calendar = new GregorianCalendar();
						String descricao = new String();
						char tipo = 0;
						double valor =0;
						// Loop continua enquanto a leitura de movimentacoes não está completa
						 do {
							contadorMovimentacoes ++;
							

							switch(contadorMovimentacoes) {
								case 1:
									//Data
									String Data = new String();
									Data = sCurrentLine;
									String dia = Data.substring(0, Data.indexOf("/"));
									String mes = Data.substring(Data.indexOf("/")+1, Data.indexOf("/",Data.indexOf("/")+1));
									String ano = Data.substring(1+Data.indexOf("/",Data.indexOf("/")+1), Data.length());
									calendar = new GregorianCalendar(Integer.parseInt(ano), Integer.parseInt(mes) -1, Integer.parseInt(dia));
									break;
								case 2:
									//Descricao da movimentacao
									descricao = sCurrentLine;
									break;
								case 3:
									//Tipo de movimentacao
									tipo = sCurrentLine.charAt(0);
									break;
								case 4:
									//Valor de movimentacao
									valor = Double.parseDouble(sCurrentLine);
									break;
								case 5:
									//Fim de primeira movimentacao
									Movimentacao m = new Movimentacao(descricao, tipo, valor, calendar);
									listaMovCliente.add(m);
									if(sCurrentLine.startsWith("=")) {
										contadorMovimentacoes = 0;
										calendar = new GregorianCalendar();
										descricao = new String();
										tipo = 0;
										valor =0;
									}else {
										leituraMovimentacoesCompleta = true;
									}

									break;
									
							}
						} while ((sCurrentLine = br.readLine()) != null && !leituraMovimentacoesCompleta);
					}

					//Adicionar cliente e conta respectivas
					Cliente c = new Cliente(nome, cpfOucnpj, endereco, fone);
					insereCliente(c);
					carregarConta(c, saldo, listaMovCliente);
					
					// Se essa próxima linha é ---- de novo, é indicacão que
					// terminou a leitura de contas e começou a leitura de clientes sem conta
					if(sCurrentLine.startsWith("-")) {
						
						leituraContasCompleta = true;
					}
				
				} //Fim de loop de leitura de Contas
				
			}//Fim do if-else para leitura de contas(if para quando nao tem conta e else para quando tem)
				
			
			
			//Leitura de clientes sem conta
			boolean leituraPrimeirasLinhas_ClientesSemConta = false;
			boolean leituraClientes_semConta_completa = false;
			int lineCounterSC = 0;
			
			while(((sCurrentLine = br.readLine()) != null) && !leituraPrimeirasLinhas_ClientesSemConta) {
				
				lineCounterSC++;
				if(lineCounterSC == 1) {
					//Primeiras linhas lidas.
					leituraPrimeirasLinhas_ClientesSemConta = true;
				}
			}
			if((sCurrentLine = br.readLine())==null) {
				//Significa que nenhum cliente não tem conta neste banco.
				leituraClientes_semConta_completa = true;
			}else {
				//Começa-se a leitura dos clientes sem conta
				
				while(!leituraClientes_semConta_completa) {
					
					int linesCliente = 0;
					boolean leituraInformacoesClienteIndividual = false;
					do {
						linesCliente++;
						switch(linesCliente) {
							case 1:
								nome = sCurrentLine;
								break;
							case 2:
								cpfOucnpj = sCurrentLine;
								break;
							case 3:
								endereco = sCurrentLine;
								break;
							case 4:
								fone = sCurrentLine;
								leituraInformacoesClienteIndividual = true;
								break;
							default:
								linesCliente = 0;
								
						}//Fim do switch
						
						
				}while ((sCurrentLine = br.readLine()) != null && !leituraInformacoesClienteIndividual);
				//Fim do while para leitura de informações de um cliente INDIVIDUAL
				
					Cliente c = new Cliente(nome, cpfOucnpj, endereco, fone);
					insereCliente(c);
					

				if((sCurrentLine = br.readLine()) == null) {
					leituraClientes_semConta_completa = true;
				}
			}//Fim de TODAS as leituras de clientes sem conta
			
		}	

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}
		
	}
}
