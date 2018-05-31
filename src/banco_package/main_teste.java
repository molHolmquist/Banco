package banco_package;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class main_teste {

	public static void main(String[] args) {
		
		Cliente juse = new Cliente("Juse","07320176610","Rua Jose Afonso","3241-5264");
//		Conta contaJuse = new Conta(juse);
//		contaJuse.creditaConta(50, "Valor recebido por loucura do juse.");
//		contaJuse.creditaConta(300, "Vendeu a bicicleta.");
//		contaJuse.creditaConta(5000, "Ganhou na loteria.");
//		contaJuse.creditaConta(200, "Achou no chao.");
		
		Cliente arnaldo = new Cliente("Arnaldo","09087987709","Rua Jose Afonso","3241-5264");
//		Conta contaArnaldo = new Conta(arnaldo);
//		contaArnaldo.creditaConta(100,  "Restaurante Boi dos Vales");
//		contaArnaldo.creditaConta(50,  "Restaurante Boi dos Vales");
//		contaArnaldo.creditaConta(20,  "Arroz");
//		contaArnaldo.creditaConta(300,  "Batata");

		GregorianCalendar calendar = new GregorianCalendar();
		
		Banco bradesco = new Banco("bradesco"); 
		bradesco.insereCliente(juse);
		bradesco.criaConta(juse);
		bradesco.insereCliente(arnaldo);
		bradesco.criaConta(arnaldo);
		bradesco.deposita(1, 100000);
		bradesco.saca(1, 100);
		bradesco.transferencia(1, 2, 100);
		bradesco.cobraCPMF();
		
		System.out.println(bradesco.saldoConta(1));
		
//		int mYear = calendar.get(Calendar.YEAR);
//		int mMonth = calendar.get(Calendar.MONTH);
//		int mDay = calendar.get(Calendar.DAY_OF_MONTH);
//		
//		System.out.println(mDay + "/" + mMonth + "/" + mYear + " ");
//
//		
//		for(Movimentacao m: contaArnaldo.extrato()) {
//			
//			SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
//		    fmt.setCalendar(m.getDataMov());
//		    String dateFormatted = fmt.format(m.getDataMov().getTime());
//			System.out.println(m.getDescricao() + "-mes:"+ " " + dateFormatted);
//			
//		}
		
	}

}
