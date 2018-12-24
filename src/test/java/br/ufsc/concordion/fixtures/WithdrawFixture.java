package br.ufsc.concordion.fixtures;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

import br.ufsc.model.ATM;
import br.ufsc.model.PinHistory;

@RunWith(ConcordionRunner.class)
public class WithdrawFixture {
	/*public String customerName;
	public String accountNumber;
	public double accountBalance;
	public String bankCardNumber;
	public int bankCardPIN;*/
	
	public ATM atm;
	//public int pin;
	//public int amount;
	
	public void setCustomer(String customerName, String accountNumber, double accountBalance, String bankCardNumber, int bankCardPIN) {
		atm = new ATM();
		atm.getBank().createBankCustomer(1, customerName, bankCardNumber, bankCardPIN, accountNumber, accountBalance);
	}
	
	public void setAvailableBills(String bills) {
		String[] arrayBills = bills.split(",");
		atm.chargeBills(Integer.parseInt(arrayBills[0]), Integer.parseInt(arrayBills[1]), Integer.parseInt(arrayBills[2]), Integer.parseInt(arrayBills[3]), Integer.parseInt(arrayBills[4]));
	}
	
	public String withdraw(String bankCardNumber, int pin, int amount) {
		try {
			atm.withDraw(bankCardNumber, pin, amount);
			return "A transação foi completada com sucesso!";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public double getBalance(String cardNumber) throws Exception {
		try {
			return atm.getBank().getAccountByCardNumber(cardNumber).getBalance();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void setNumberOfIncorretPINWithin72Hours(String text, String bankCardNumber) throws Exception {
		atm.getBank().getCardByNumber(bankCardNumber).setPinHistory(new ArrayList<PinHistory>());
		int x = Integer.parseInt(text);
		for (int i=1; i<=x; i++) {
			atm.getBank().getCardByNumber(bankCardNumber).insertPinHistory(false, new Date());
		}
	}
}
