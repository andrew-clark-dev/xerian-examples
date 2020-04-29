package com.xerian.examples.spring.validation.api;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.xerian.examples.spring.validation.domain.Product;
import com.xerian.examples.spring.validation.domain.State;

import java.time.LocalDateTime;

public class ProductCreateRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String sku;
    @Min(0)
    private Integer price;

    public ProductCreateRequest() {
    }

    public ProductCreateRequest(String sku, String name, Integer price) {
        this.sku = sku;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getSku() {
        return sku;
    }

    public Product toProduct() {
        return new Product(
                getSku(),
                getName(),
                getPrice(),
                0,
                State.CAPTURED,
                LocalDateTime.now()
        );
    }
}
