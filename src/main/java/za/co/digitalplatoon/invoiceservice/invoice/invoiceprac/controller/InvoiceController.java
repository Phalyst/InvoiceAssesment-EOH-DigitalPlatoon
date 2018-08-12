package za.co.digitalplatoon.invoiceservice.invoice.invoiceprac.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import za.co.digitalplatoon.invoiceservice.invoice.invoiceprac.service.InvoiceService;
import za.co.digitalplatoon.invoiceservice.invoice.invoiceprac.entity.Invoice;
import za.co.digitalplatoon.invoiceservice.invoice.invoiceprac.model.InvoiceModel;


@RestController
public class InvoiceController {
	
	@Autowired
	private InvoiceService invoiceService;
	
	@PostMapping("/invoices")
	public InvoiceModel addInvoice(@RequestBody InvoiceModel invoiceModel){
		InvoiceModel savedInvoice = invoiceService.addInvoice(invoiceModel );

		return savedInvoice;
	}
	
	@GetMapping("/invoices")
	public List<InvoiceModel> viewAllInvoice(){
		
		return invoiceService.viewAllInvoice();
	}
	
	@GetMapping("/invoices/{id}")
	public InvoiceModel viewInvoice(@PathVariable long id) throws Exception {
		InvoiceModel invoice = invoiceService.viewInvoice(id);
		return invoice;
	}
}
