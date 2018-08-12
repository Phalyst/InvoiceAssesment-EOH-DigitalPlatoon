package za.co.digitalplatoon.invoiceservice.invoice.invoiceprac.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;

import za.co.digitalplatoon.invoiceservice.invoice.invoiceprac.entity.Invoice;
import za.co.digitalplatoon.invoiceservice.invoice.invoiceprac.entity.LineItem;
import za.co.digitalplatoon.invoiceservice.invoice.invoiceprac.model.InvoiceModel;
import za.co.digitalplatoon.invoiceservice.invoice.invoiceprac.repository.InvoiceRepository;
import za.co.digitalplatoon.invoiceservice.invoice.invoiceprac.repository.LineItemRepository;

@Repository
public class InvoiceServiceImpl implements InvoiceService{
	
	private final Logger log = LoggerFactory.getLogger(InvoiceServiceImpl.class);
	@Autowired
	private InvoiceRepository invoiceRepository;
	@Autowired
	private LineItemRepository lineItemRepository;
	
	@Override
	public InvoiceModel addInvoice(InvoiceModel invoiceModel) {
		Invoice savedInvoice = invoiceRepository.save(new Invoice(invoiceModel));
		InvoiceModel newInvoiceModel = new InvoiceModel(savedInvoice);

	    for(LineItem item : invoiceModel.getLineItem()) {
	    	item.setInvoice(savedInvoice);
	    	newInvoiceModel.getLineItem().add(lineItemRepository.save(item));
	    }
        
		return invoiceSummary(newInvoiceModel);
	}
	
	@Override
	public List<InvoiceModel> viewAllInvoice() {
		List<InvoiceModel> list = new ArrayList<>();
		for(Invoice model: invoiceRepository.findAll()) {
			List<LineItem> items = this.findLineItemByInvoiceID(model.getId());
			InvoiceModel invoiceModelnew = new InvoiceModel(model);
			invoiceModelnew.getLineItem().addAll(items);

			list.add(invoiceSummary(invoiceModelnew));
		}
		
		return list;
	}
	private List<LineItem> findLineItemByInvoiceID(Long id) {
		List<LineItem> items = lineItemRepository.findAll();
		for(LineItem item : items) {
			if(item.getInvoice() != null )
			if(item.getInvoice().getId() != id) {
				items.remove(item);
			}
		}
		return items;
	}

	@Override
	public InvoiceModel viewInvoice(Long id) throws Exception {
		Optional<Invoice> invoice = invoiceRepository.findById(id);
		if (!invoice.isPresent())
			throw new InvoiceNotFoundException("invoice not found with id :" + id);
		
		List<LineItem> items = this.findLineItemByInvoiceID(id);
		InvoiceModel invoiceModelnew = new InvoiceModel(invoice.get());
		invoiceModelnew.getLineItem().addAll(items);
		
		return  invoiceSummary(invoiceModelnew);
	}
	
	public InvoiceModel invoiceSummary(InvoiceModel invoiceModel) {
		
		BigDecimal subTotal =BigDecimal.ZERO;
		BigDecimal vat =BigDecimal.ZERO;
		double rate = invoiceModel.getVatRate()/100;

	    for(LineItem item : invoiceModel.getLineItem()) {
	    	subTotal = subTotal.add(item.getLineItemTotal());
	    	subTotal =subTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
	    	item.setInvoice(null);
	    }
	    BigDecimal vatRated = new BigDecimal(String.valueOf(rate));
	    vat =vat.add(subTotal.multiply(vatRated));
	    invoiceModel.setSubTotal(subTotal);
	    invoiceModel.setVat(vat);
	    invoiceModel.setTotal(subTotal.add(vat));
	    
		return invoiceModel;
		
	}

}
