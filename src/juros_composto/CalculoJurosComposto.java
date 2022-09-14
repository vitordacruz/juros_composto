package juros_composto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculoJurosComposto {
	
	private static final BigDecimal JUROS = BigDecimal.valueOf(1.15D);
	private static final int QUANTIDADE_CASAS_DECIMAIS = 2;

	public static void main(String[] args) {
		var valorInicial = new BigDecimal(12000);
		
		var valorPago = new BigDecimal("0.0");
		
		var valorDivida = BigDecimal.valueOf(valorInicial.doubleValue());
		
		for (int mes = 1; valorDivida.doubleValue() > 0.0; mes++) {
			
			var rendimento = BigDecimal.valueOf(100).add(JUROS)
					.divide(BigDecimal.valueOf(100)).setScale((QUANTIDADE_CASAS_DECIMAIS + 2), RoundingMode.HALF_UP);
			
			valorDivida = valorDivida.multiply(rendimento);
			
			valorDivida = valorDivida.setScale(QUANTIDADE_CASAS_DECIMAIS, RoundingMode.HALF_UP);
			
			var valorParcela = new BigDecimal(500).multiply(rendimento);
			
			if (valorDivida.subtract(valorParcela).doubleValue() <= 0.0) {
				valorParcela = valorDivida;
			}
			
			valorParcela = valorParcela.setScale(QUANTIDADE_CASAS_DECIMAIS, RoundingMode.HALF_UP);
			
			valorDivida = valorDivida.subtract(valorParcela);
			
			valorPago = valorPago.add(valorParcela);
		
			System.out.println("------------------------------------");
			System.out.println("mês: " + mes);
			System.out.println("valorDivida: " + valorDivida);
			System.out.println("valor da parcela: " + valorParcela);
			System.out.println("Valor Total Pago: " + valorPago);
			
		}
		
		System.out.println("------------------------------------");
		System.out.println("\nvalorPago: " + valorPago);
		System.out.println("Lucro: " + (valorPago.subtract(valorInicial)));

	}

}
