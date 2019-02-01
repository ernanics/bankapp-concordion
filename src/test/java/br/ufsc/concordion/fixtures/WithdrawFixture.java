package br.ufsc.concordion.fixtures;


import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

import br.ufsc.model.ATM;


@RunWith(ConcordionRunner.class)
public class WithdrawFixture {
	
	public ATM atm;

	public void setCustomer( String customerName, String accountNumber, double accountBalance, String bankCardNumber, int bankCardPIN) {
		atm = new ATM();
		atm.getBank().createBankCustomer(1, customerName, bankCardNumber, bankCardPIN, accountNumber, accountBalance);
	}
	
	public void setAvailableBills(String bills) {
		String[] arrayBills = bills.split(",");
		atm.chargeBills(Integer.parseInt(arrayBills[0]), Integer.parseInt(arrayBills[1]), Integer.parseInt(arrayBills[2]), Integer.parseInt(arrayBills[3]), Integer.parseInt(arrayBills[4]));
	}

	public String withdraw(String bankCardNumber, int pin, int amount) throws Exception {
		try {
			Map<Integer, Integer> wd = atm.withDraw(bankCardNumber, pin, amount);
			return "A transação foi completada com sucesso!";
		}
		catch(Exception ex) {
			return ex.getMessage();
		}
	}
	
	public double getBalance(String bankCardNumber) throws Exception {
		return atm.getBank().getAccountByCardNumber(bankCardNumber).getBalance();
	}
	
	public void setNumberOfIncorretPINWithin72Hours(String TEXT, String bankCardNumber) {
//		atm.getBank().getCardByNumber(bankCardNumber).
	}
}
