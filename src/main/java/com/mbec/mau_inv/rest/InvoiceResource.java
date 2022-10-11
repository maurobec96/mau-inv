package com.mbec.mau_inv.rest;

import com.mbec.mau_inv.model.InvoiceDTO;
import com.mbec.mau_inv.service.InvoiceService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/invoices", produces = MediaType.APPLICATION_JSON_VALUE)
public class InvoiceResource {

    private final InvoiceService invoiceService;

    public InvoiceResource(final InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public ResponseEntity<List<InvoiceDTO>> getAllInvoices() {
        return ResponseEntity.ok(invoiceService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDTO> getInvoice(@PathVariable final Long id) {
        return ResponseEntity.ok(invoiceService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createInvoice(@RequestBody @Valid final InvoiceDTO invoiceDTO) {
        return new ResponseEntity<>(invoiceService.create(invoiceDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateInvoice(@PathVariable final Long id,
            @RequestBody @Valid final InvoiceDTO invoiceDTO) {
        invoiceService.update(id, invoiceDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable final Long id) {
        invoiceService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
