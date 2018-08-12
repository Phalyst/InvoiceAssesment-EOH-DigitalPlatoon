package za.co.digitalplatoon.invoiceservice.invoice.invoiceprac.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import za.co.digitalplatoon.invoiceservice.invoice.invoiceprac.model.InvoiceModel;

import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;

@Entity
@Table(name = "invoice")
public class Invoice implements Serializable{
	@Id
	@GeneratedValue
	private Long id;
	
	private String client;
	private double vatRate;
	private Date invoiceDate;

	public Invoice(){
    	
    }
	
	public Invoice(InvoiceModel invoiceModel) {
		this.setClient(invoiceModel.getClient());
		this.setVatRate(invoiceModel.getVatRate());
		this.setInvoiceDate(invoiceModel.getInvoiceDate());
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
	
	

}
