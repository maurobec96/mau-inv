package com.mbec.mau_inv.model;

public class ProductGetDTO extends ProductDTO {

    private Long existingAmount;

    public Long getExistingAmount() {
        return existingAmount;
    }

    public void setExistingAmount(Long existingAmount) {
        this.existingAmount = existingAmount;
    }

}
