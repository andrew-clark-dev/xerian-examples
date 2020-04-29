package com.xerian.examples.spring.validation.api;

import javax.validation.constraints.NotNull;

import com.xerian.examples.spring.validation.domain.Product;
import com.xerian.examples.spring.validation.domain.State;

public class ProductPatchRequest {

    @NotNull
    private State state;

    public ProductPatchRequest() {
    }

    public ProductPatchRequest(String sku, State state) {
        this.state = state;
    }


	public State getState() {
		return state;
	}

	public Product patchProduct(Product product) {
		product.setState(state);
		return product;
	}


}
