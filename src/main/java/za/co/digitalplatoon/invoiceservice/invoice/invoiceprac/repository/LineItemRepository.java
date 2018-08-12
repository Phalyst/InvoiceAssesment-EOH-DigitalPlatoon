package za.co.digitalplatoon.invoiceservice.invoice.invoiceprac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.digitalplatoon.invoiceservice.invoice.invoiceprac.entity.Invoice;
import za.co.digitalplatoon.invoiceservice.invoice.invoiceprac.entity.LineItem;

@Repository
public interface LineItemRepository extends JpaRepository<LineItem, Long>{


}
