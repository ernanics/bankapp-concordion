package br.ufsc.concordion.fixtures;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

import br.ufsc.model.ATM;
import br.ufsc.model.Loan;

@RunWith(ConcordionRunner.class)
public class EMICalculatorFixture {
	public String customerName;
	public String accountNumber;
	public double accountBalance;
	public String bankCardNumber;
	public int bankCardPIN;
	
	public Double amount;
	public Double interestRate;
	public Long numberOfMonthlyInstallments;
	public Loan loan;
	
	public ATM atm;

	public void setCustomer(String customerName, String accountNumber, double accountBalance, String bankCardNumber, int bankCardPIN) {
		atm = new ATM();
		atm.getBank().createBankCustomer(1, customerName, bankCardNumber, bankCardPIN, accountNumber, accountBalance);
	}
	
	public Map<String, String> calculateEMI(Double amount, Double interestRate, Long numberOfMonthlyInstallments) {
		loan = new Loan(amount, interestRate, numberOfMonthlyInstallments);
		loan.getNumberOfMontlhyInstalments();
		loan.getLoanAmountWithInterests();
		Map<String, String> results = new HashMap<String, String>();
	    results.put("firstName", loan.getNumberOfMontlhyInstalments().toString());
	    results.put("lastName", loan.getLoanAmountWithInterests().toString());
	    return results;
	}
}