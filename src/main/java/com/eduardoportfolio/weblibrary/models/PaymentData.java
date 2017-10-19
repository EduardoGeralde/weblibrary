package com.eduardoportfolio.weblibrary.models;

import java.math.BigDecimal;

/**
 * 
 * @author Eduardo Geralde Neto
 * 
 *
 */

public class PaymentData {
	
	private BigDecimal value;
	
	public PaymentData(){
		
	}
	
	public PaymentData(BigDecimal value){
		this.value = value;
	}
	
	public BigDecimal getValue(){
		return value;
	}

}
