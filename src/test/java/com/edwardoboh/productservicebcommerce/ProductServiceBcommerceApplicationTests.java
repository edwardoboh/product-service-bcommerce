package com.edwardoboh.productservicebcommerce;

import com.edwardoboh.productservicebcommerce.dto.ProductRequest;
import com.edwardoboh.productservicebcommerce.dto.ProductResponse;
import com.edwardoboh.productservicebcommerce.repository.ProductRepository;
import com.edwardoboh.productservicebcommerce.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceBcommerceApplicationTests {
	@Container
	private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo");

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductRepository productRepository;
	private final ObjectMapper objectMapper = new ObjectMapper();

	@DynamicPropertySource
	private static void dynamicProps(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	void createProduct() throws Exception{
		mockMvc.perform(
				MockMvcRequestBuilders.post("/api/product")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(generateProductRequest())))
				.andExpect(status().isCreated());
		List<ProductResponse> productList = productService.getProducts();
		System.out.println(productList);
		Assertions.assertEquals(1, productList.size());
	}

	@Test
	void fetchProducts() throws  Exception{
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/product"))
				.andExpect(status().isOk());
	}
	private ProductRequest generateProductRequest() {
		return ProductRequest.builder()
				.name("First Product")
				.description("A short description for the first product")
				.price(new BigDecimal(233.4))
				.build();
	}

}
