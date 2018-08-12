package za.co.digitalplatoon.invoiceservice.invoice.invoiceprac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.digitalplatoon.invoiceservice.invoice.invoiceprac.entity.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>{

}
