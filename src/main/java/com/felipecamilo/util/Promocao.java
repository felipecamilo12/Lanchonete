package com.felipecamilo.util;

import java.math.BigDecimal;

public class Promocao {

	public static BigDecimal calculaLight(BigDecimal alface, BigDecimal bacon) {
		BigDecimal promocao = new BigDecimal("0.0");
		if ((alface.intValue() > 0) && (bacon.intValue() == 0)) {
			promocao = new BigDecimal("0.1");
		}
		return promocao;
	}

	public static BigDecimal calcularQuantidadePagar(BigDecimal quantidade) {
		BigDecimal ingredientesPagar = quantidade;
		if (quantidade.intValue() >= 3) {
			if (Math.floorMod(quantidade.intValue(), 3) == 0) {
				ingredientesPagar = (ingredientesPagar.divide(new BigDecimal("1.5")));
			}
		}
		return ingredientesPagar;
	}

}
