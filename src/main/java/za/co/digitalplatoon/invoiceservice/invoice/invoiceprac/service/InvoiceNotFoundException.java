package za.co.digitalplatoon.invoiceservice.invoice.invoiceprac.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvoiceNotFoundException extends RuntimeException  {
	
	  public InvoiceNotFoundException(String exception) {
		    super(exception);
	  }
}
