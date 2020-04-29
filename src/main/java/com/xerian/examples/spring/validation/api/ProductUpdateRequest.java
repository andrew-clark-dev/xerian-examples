package com.xerian.examples.spring.validation.api;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.xerian.examples.spring.validation.domain.Product;

public class ProductUpdateRequest {
    
	@NotBlank
    private String name;
    
    @NotNull
    @Min(0)
    private Integer price;
    
    @Min(0)
    private Integer stock;

    public ProductUpdateRequest() {
    }

    public ProductUpdateRequest(String sku, String name, Integer price, Integer stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

	/**
	 * @return the stock
	 */
	public Integer getStock() {
		return stock;
	}

	public Product updateProduct(Product product) {
		product.setName(name);
		product.setPrice(price);
		product.setStock(stock);
		return product;
	}

}
