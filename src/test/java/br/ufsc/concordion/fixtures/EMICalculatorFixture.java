package br.ufsc.concordion.fixtures;

import java.util.HashMap;
import java.util.Map;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

import br.ufsc.model.ATM;
import br.ufsc.model.Loan;

@RunWith(ConcordionRunner.class)
public class EMICalculatorFixture {
	
	public Loan loan;
	public ATM atm;

	//before
	public void setCustomer(String customerName, String accountNumber, double accountBalance, String bankCardNumber, int bankCardPIN) {
		atm = new ATM();
		atm.getBank().createBankCustomer(1, customerName, bankCardNumber, bankCardPIN, accountNumber, accountBalance);
	}

	//set available bills
	public void setAvailableBills(String bills) {
		String[] arrayBills = bills.split(",");
		atm.chargeBills(Integer.parseInt(arrayBills[0]), Integer.parseInt(arrayBills[1]), Integer.parseInt(arrayBills[2]), Integer.parseInt(arrayBills[3]), Integer.parseInt(arrayBills[4]));
	}
	
	public Map<String, String> calculateEMI(Double amount, Double interestRate, Long numberOfMonthlyInstallments) {
		loan = new Loan(amount, interestRate, numberOfMonthlyInstallments);
		loan.getNumberOfMontlhyInstalments();
		loan.getLoanAmountWithInterests();
		Map<String, String> results = new HashMap<String, String>();
	    results.put("emiValue", loan.getValueOfMontlhyInstalments().toString());
	    results.put("loanAmountWithInterest", loan.getLoanAmountWithInterests().toString());
	    return results;
	}
	
//	public void calculateEMI(String amout, String interestRate, String numberOfMonthlyInstallments) {
//		
//	}
}
