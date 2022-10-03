/**
 * 
 */
package com.spring.tiendafer.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Jonatan
 *
 */
@Entity
@Table(name = "client_bill_has_payment")
public class ClientBillHasPayment {
	//Declaracion de variables
	@Id
	private int idClientBillHasPayment;
	private int totalOutstandingBalance;
	private int balanceCanceled;
	private int newOutstandingBalance;
	private int changeCash;
	@OneToOne
	@JoinColumn(name = "id_payment")
	private Payment payment;
	@OneToOne
	@JoinColumn(name = "id_client_bill")
	private ClientBill clientBill;
	
	//Metodo constructor
	public ClientBillHasPayment(int idClientBillHasPayment, int totalOutstandingBalance, int balanceCanceled,
			int newOutstandingBalance, int changeCash, Payment payment, ClientBill clientBill) {
		super();
		this.idClientBillHasPayment = idClientBillHasPayment;
		this.totalOutstandingBalance = totalOutstandingBalance;
		this.balanceCanceled = balanceCanceled;
		this.newOutstandingBalance = newOutstandingBalance;
		this.changeCash = changeCash;
		this.payment = payment;
		this.clientBill = clientBill;
	}

	public ClientBillHasPayment() {
		
	}

	//Get y set
	public int getIdClientBillHasPayment() {
		return idClientBillHasPayment;
	}

	public void setIdClientBillHasPayment(int idClientBillHasPayment) {
		this.idClientBillHasPayment = idClientBillHasPayment;
	}

	public int getTotalOutstandingBalance() {
		return totalOutstandingBalance;
	}

	public void setTotalOutstandingBalance(int totalOutstandingBalance) {
		this.totalOutstandingBalance = totalOutstandingBalance;
	}

	public int getBalanceCanceled() {
		return balanceCanceled;
	}

	public void setBalanceCanceled(int balanceCanceled) {
		this.balanceCanceled = balanceCanceled;
	}

	public int getNewOutstandingBalance() {
		return newOutstandingBalance;
	}

	public void setNewOutstandingBalance(int newOutstandingBalance) {
		this.newOutstandingBalance = newOutstandingBalance;
	}

	public int getChangeCash() {
		return changeCash;
	}

	public void setChangeCash(int changeCash) {
		this.changeCash = changeCash;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public ClientBill getClientBill() {
		return clientBill;
	}

	public void setClientBill(ClientBill clientBill) {
		this.clientBill = clientBill;
	}
}
