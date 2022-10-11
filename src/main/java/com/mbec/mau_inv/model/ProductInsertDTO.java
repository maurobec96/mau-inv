package com.mbec.mau_inv.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class ProductInsertDTO extends ProductDTO {

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal cost;

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(final BigDecimal cost) {
        this.cost = cost;
    }

}
