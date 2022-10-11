package com.mbec.mau_inv.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


public class InvoiceDTO {

    private Long id;
    private OffsetDateTime dueDate;
    private Long client;
    private List<InvoiceProductDTO> products = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public OffsetDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(final OffsetDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public Long getClient() {
        return client;
    }

    public void setClient(final Long client) {
        this.client = client;
    }

    public List<InvoiceProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<InvoiceProductDTO> products) {
        this.products = products;
    }

}
