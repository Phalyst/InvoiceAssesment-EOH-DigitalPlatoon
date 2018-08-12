package za.co.digitalplatoon.invoiceservice.invoice.invoiceprac.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import za.co.digitalplatoon.invoiceservice.invoice.invoiceprac.entity.Invoice;
import za.co.digitalplatoon.invoiceservice.invoice.invoiceprac.model.InvoiceModel;


public interface InvoiceService {
	
  InvoiceModel addInvoice(InvoiceModel invoice);
  List<InvoiceModel> viewAllInvoice();
  InvoiceModel viewInvoice(Long id) throws Exception;
  
}
