package com.mbec.mau_inv.service;

import com.mbec.mau_inv.domain.Product;
import com.mbec.mau_inv.model.ProductInsertDTO;
import com.mbec.mau_inv.repos.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductInsertDTO> findAll() {
        return productRepository.findAll(Sort.by("id"))
                .stream()
                .map(product -> mapToDTO(product, new ProductInsertDTO()))
                .collect(Collectors.toList());
    }

    public ProductInsertDTO get(final Long id) {
        return productRepository.findById(id)
                .map(product -> mapToDTO(product, new ProductInsertDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final ProductInsertDTO productDTO) {
        final Product product = new Product();
        mapToEntity(productDTO, product);
        return productRepository.save(product).getId();
    }

    public void update(final Long id, final ProductInsertDTO productDTO) {
        final Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(productDTO, product);
        productRepository.save(product);
    }

    public void delete(final Long id) {
        productRepository.deleteById(id);
    }

    private ProductInsertDTO mapToDTO(final Product product, final ProductInsertDTO productDTO) {
        productDTO.setId(product.getId());
        productDTO.setProductName(product.getProductName());
        productDTO.setProductDescription(product.getProductDescription());
        productDTO.setCost(product.getCost());
        productDTO.setListPrice(product.getListPrice());
        return productDTO;
    }

    private Product mapToEntity(final ProductInsertDTO productDTO, final Product product) {
        product.setProductName(productDTO.getProductName());
        product.setProductDescription(productDTO.getProductDescription());
        product.setCost(productDTO.getCost());
        product.setListPrice(productDTO.getListPrice());
        return product;
    }

}
