/**
 * 
 */
package com.spring.tiendafer.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Jonatan
 *
 */
@Entity
@Table(name = "detail_order_bill")
public class DetailOrderBill {
	//Declaracion de variables
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDetailOrderBill;
	private int orderedQuantity;
	private int receivedQuantity;
	private int unitValue;
	private float percentageProfit;
	private int totalValue;
	@OneToOne
	@JoinColumn(name = "id_product")
	private Product product;
	@OneToOne
	@JoinColumn(name = "id_order_bill")
	private OrderBill orderBill;
	
	//Metodo constructor
	public DetailOrderBill() {
		
	}

	public DetailOrderBill(int orderedQuantity, int receivedQuantity, int unitValue,
			float percentageProfit, int totalValue, Product product, OrderBill orderBill) {
		super();
		this.orderedQuantity = orderedQuantity;
		this.receivedQuantity = receivedQuantity;
		this.unitValue = unitValue;
		this.percentageProfit = percentageProfit;
		this.totalValue = totalValue;
		this.product = product;
		this.orderBill = orderBill;
	}

	//Get y set
	public int getIdDetailOrderBill() {
		return idDetailOrderBill;
	}

	public void setIdDetailOrderBill(int idDetailOrderBill) {
		this.idDetailOrderBill = idDetailOrderBill;
	}

	public int getOrderedQuantity() {
		return orderedQuantity;
	}

	public void setOrderedQuantity(int orderedQuantity) {
		this.orderedQuantity = orderedQuantity;
	}

	public int getReceivedQuantity() {
		return receivedQuantity;
	}

	public void setReceivedQuantity(int receivedQuantity) {
		this.receivedQuantity = receivedQuantity;
	}

	public int getUnitValue() {
		return unitValue;
	}

	public void setUnitValue(int unitValue) {
		this.unitValue = unitValue;
	}

	public float getPercentageProfit() {
		return percentageProfit;
	}

	public void setPercentageProfit(float percentageProfit) {
		this.percentageProfit = percentageProfit;
	}

	public int getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(int totalValue) {
		this.totalValue = totalValue;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public OrderBill getOrderBill() {
		return orderBill;
	}

	public void setOrderBill(OrderBill orderBill) {
		this.orderBill = orderBill;
	}
}
