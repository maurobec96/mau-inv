package com.mbec.mau_inv.model;

public class InvoiceProductDTO {

    private Long productId;
    private Long amountPurchased;
    private ProductDTO product;

    public void setProductId(Long productId){
        this.productId = productId;
    }

    public Long getProductId(){
        return productId;
    }

    public void setAmountPurchased(Long amountPurchased) {
        this.amountPurchased = amountPurchased;
    }

    public Long getAmountPurchased(){
        return amountPurchased;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }



}
