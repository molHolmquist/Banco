package banco_package;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class main_teste {

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
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(data.getTimeInMillis());
		
		int mYear = calendar.get(Calendar.YEAR);
		int mMonth = calendar.get(Calendar.MONTH);
		int mDay = calendar.get(Calendar.DAY_OF_MONTH);
		
		System.out.println(mDay + "/" + mMonth + "/" + mYear);

		/*
		for(Movimentacao m: contaArnaldo.extrato(data)) {
			
			SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
		    fmt.setCalendar(m.getDataMov());
		    String dateFormatted = fmt.format(m.getDataMov().getTime());
			System.out.println(m.getDescricao() + "-mes:"+ " " + dateFormatted);
			
		}
		*/
	}

}
