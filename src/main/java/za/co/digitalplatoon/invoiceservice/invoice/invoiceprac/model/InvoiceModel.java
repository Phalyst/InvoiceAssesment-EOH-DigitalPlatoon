package za.co.digitalplatoon.invoiceservice.invoice.invoiceprac.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import za.co.digitalplatoon.invoiceservice.invoice.invoiceprac.entity.Invoice;
import za.co.digitalplatoon.invoiceservice.invoice.invoiceprac.entity.LineItem;


public class InvoiceModel implements Serializable {

	private Long id;
	
	private String client;
	private double vatRate;
	private Date invoiceDate;
	private BigDecimal subTotal;
	private BigDecimal vat ;
	private BigDecimal total; 

	private List<LineItem> lineItem;
    
    public List<LineItem> getLineItem() {
		return lineItem;
	}

	public void setLineItem(List<LineItem> lineItem) {
		this.lineItem = lineItem;
	}

	public InvoiceModel(){
    	
    }
	
	public InvoiceModel(Invoice invoiceModel) {
		    this.setId(invoiceModel.getId());
			this.setClient(invoiceModel.getClient());
			this.setVatRate(invoiceModel.getVatRate());
			this.setInvoiceDate(invoiceModel.getInvoiceDate());
			this.lineItem = new ArrayList<>();
			this.subTotal = BigDecimal.ZERO;
			this.vat = BigDecimal.ZERO;
			this.total= BigDecimal.ZERO; 
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public Double getVatRate() {
		return vatRate;
	}
	public void setVatRate(double vatRate) {
		this.vatRate = vatRate;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public void setVat(BigDecimal vat) {
		this.vat = vat;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getSubTotal() {
		return subTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public BigDecimal getVat() {
		return vat.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public BigDecimal getTotal() {
		return total.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**	
	public void Calculate() {
		this.subTotal.add(this.getSubTotal());
		this.vat = this.getVat();
		this.total = this.getTotal();
	}
	public BigDecimal getSubTotal() {
		
		for(LineItem item : this.lineItem) {
			this.subTotal.add(item.getUnitPrice());
			//this.subTotal.add(new BigDecimal(item.getUnitPrice() * item.getQuantity()));
		}
		return this.subTotal;
	}
	
	public BigDecimal getVat() {
		
        double rate = vatRate/100;
        // subtotal*vatRate/100;
		return this.subTotal.multiply(new BigDecimal(String.valueOf(rate))) ;
	}
	
	
	public BigDecimal getTotal() {
		
		return this.subTotal.add(getVat());
	}
	
*/
}
