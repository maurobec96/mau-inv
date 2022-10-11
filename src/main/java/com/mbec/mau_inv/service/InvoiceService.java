package com.mbec.mau_inv.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mbec.mau_inv.domain.Client;
import com.mbec.mau_inv.domain.Invoice;
import com.mbec.mau_inv.domain.InvoiceProduct;
import com.mbec.mau_inv.domain.Product;
import com.mbec.mau_inv.model.InvoiceDTO;
import com.mbec.mau_inv.model.InvoiceProductDTO;
import com.mbec.mau_inv.model.ProductDTO;
import com.mbec.mau_inv.repos.ClientRepository;
import com.mbec.mau_inv.repos.InvoiceRepository;
import com.mbec.mau_inv.repos.ProductRepository;


@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    public InvoiceService(final InvoiceRepository invoiceRepository,
            final ClientRepository clientRepository,
            final ProductRepository productRepository) {
        this.invoiceRepository = invoiceRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
    }

    public List<InvoiceDTO> findAll() {
        return invoiceRepository.findAll(Sort.by("id"))
                .stream()
                .map(invoice -> mapToDTO(invoice, new InvoiceDTO()))
                .collect(Collectors.toList());
    }

    public InvoiceDTO get(final Long id) {
        return invoiceRepository.findById(id)
                .map(invoice -> mapToDTO(invoice, new InvoiceDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final InvoiceDTO invoiceDTO) {
        final Invoice invoice = new Invoice();
        mapToEntity(invoiceDTO, invoice);
        return invoiceRepository.save(invoice).getId();
    }

    public void update(final Long id, final InvoiceDTO invoiceDTO) {
        final Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(invoiceDTO, invoice);
        invoiceRepository.save(invoice);
    }

    public void delete(final Long id) {
        invoiceRepository.deleteById(id);
    }

    private InvoiceDTO mapToDTO(final Invoice invoice, final InvoiceDTO invoiceDTO) {
        invoiceDTO.setId(invoice.getId());
        invoiceDTO.setDueDate(invoice.getDueDate());
        invoiceDTO.setClient(invoice.getClient() == null ? null : invoice.getClient().getId());

        invoiceDTO.setProducts(invoice.getInvoiceInvoiceProducts().stream().map(invoiceProduct ->{

            Product product = invoiceProduct.getProduct();
            ProductDTO productDto = new ProductDTO();
            productDto.setId(product.getId());
            productDto.setListPrice(product.getListPrice());
            productDto.setProductName(product.getProductName());
            productDto.setProductDescription(product.getProductDescription());

            InvoiceProductDTO invProDto = new InvoiceProductDTO();
            invProDto.setProduct(productDto);
            invProDto.setAmountPurchased(invoiceProduct.getAmountPurchased());
            invProDto.setProductId(product.getId());
            return invProDto;

        }).collect(Collectors.toList()));

        return invoiceDTO;
    }

    private Invoice mapToEntity(final InvoiceDTO invoiceDTO, final Invoice invoice) {
        invoice.setDueDate(invoiceDTO.getDueDate());
        final Client client = invoiceDTO.getClient() == null ? null : clientRepository.findById(invoiceDTO.getClient())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "client not found"));
        invoice.setClient(client);

        invoiceDTO.getProducts().stream().forEach(invoiceProductDTO -> {
            Product product = productRepository.findById(invoiceProductDTO.getProductId()).orElseThrow(()
                    -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

            InvoiceProduct invPro = new InvoiceProduct();
            invPro.setProduct(product);
            invPro.setAmountPurchased(invoiceProductDTO.getAmountPurchased());
            invoice.addInvoiceInvoiceProducts(invPro);

        });


        return invoice;
    }

}
