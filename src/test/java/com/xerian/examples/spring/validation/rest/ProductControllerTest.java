package com.xerian.examples.spring.validation.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xerian.examples.spring.validation.api.ProductCreateRequest;
import com.xerian.examples.spring.validation.apierror.validation.ProductCreateRequestValidator;
import com.xerian.examples.spring.validation.apierror.validation.ProductPatchRequestValidator;
import com.xerian.examples.spring.validation.apierror.validation.ProductUpdateRequestValidator;
import com.xerian.examples.spring.validation.repository.ProductRepository;
import com.xerian.examples.spring.validation.service.ProductService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

	private MockMvc mvc;

	@MockBean
	private ProductService productService;

	@MockBean
	private ProductRepository productRepository;

	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * Set up the mvc mock.
	 *
	 * @throws Exception should not occur
	 */
	@Before
	public void setup() throws Exception {
		// Create the validators
		ProductCreateRequestValidator productCreateRequestValidator = new ProductCreateRequestValidator(
				productRepository);
		ProductUpdateRequestValidator productUpdateRequestValidator = new ProductUpdateRequestValidator();
		ProductPatchRequestValidator productPatchRequestValidator = new ProductPatchRequestValidator();

		// Create the controller
		ProductController productController = new ProductController(
				productCreateRequestValidator,
				productUpdateRequestValidator, 
				productPatchRequestValidator, 
				productService);
		
		this.mvc = MockMvcBuilders.standaloneSetup(productController).build();
	}

	@Test
	void whenPostIsValid_thenReturnsStatus200() throws Exception {
		ProductCreateRequest createRequest = new ProductCreateRequest("sku", "name", 10);
		String body = objectMapper.writeValueAsString(createRequest);

		mvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON).content(body))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

//	@Test
//	void whenInputIsInvalid_thenReturnsStatus400() throws Exception {
//		Input input = invalidInput();
//		String body = objectMapper.writeValueAsString(input);
//
//		mvc.perform(post("/validateBody").contentType("application/json").content(body))
//				.andExpect(status().isBadRequest());
//	}
}
