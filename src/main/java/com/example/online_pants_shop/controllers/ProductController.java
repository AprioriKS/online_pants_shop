package com.example.online_pants_shop.controllers;

import com.example.online_pants_shop.controllers.constant.ShopConstants;
import com.example.online_pants_shop.dto.product.request.CreateProductDto;
import com.example.online_pants_shop.dto.product.request.UpdateProductDto;
import com.example.online_pants_shop.dto.product.response.ProductResponseDto;
import com.example.online_pants_shop.exception.DuplicateEntryException;
import com.example.online_pants_shop.exception.EntityNotFoundException;
import com.example.online_pants_shop.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

  private final ProductService productService;

  @Operation(summary = "Create a new product", description = "Saves a new product to the database.")
  @PostMapping
  public ResponseEntity<ProductResponseDto> createProduct(
      @RequestBody CreateProductDto requestDto) throws DuplicateEntryException {
    ProductResponseDto createdProduct = productService.save(requestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
  }

  @Operation(summary = "Get product by ID", description = "Retrieves a product by its ID.")
  @GetMapping("/{id}")
  public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
    ProductResponseDto product = productService.findById(id);
    return ResponseEntity.ok(product);
  }

  @Operation(summary = "Get all products", description = "Retrieves a list of all products.")
  @GetMapping
  public ResponseEntity<List<ProductResponseDto>> getAllProducts(
      @Parameter(example = ShopConstants.PAGEABLE_EXAMPLE_NAME) Pageable pageable) {
    List<ProductResponseDto> products = productService.findAll(pageable);
    return ResponseEntity.ok(products);
  }

  @Operation(summary = "Update an existing product", description = "Updates the product details.")
  @PutMapping("/{id}")
  public ResponseEntity<ProductResponseDto> updateProduct(
      @PathVariable Long id,
      @RequestBody UpdateProductDto requestDto,
      @RequestParam(defaultValue = "false") boolean areCategoriesReplaced)
      throws EntityNotFoundException {
    ProductResponseDto updatedProduct = productService.update(requestDto, id,
        areCategoriesReplaced);
    return ResponseEntity.ok(updatedProduct);
  }

  @Operation(summary = "Delete a product", description = "Deletes a product by its ID.")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    productService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "Get products by category ID", description = "Retrieves all products that belong to a specific category.")
  @GetMapping("/category/{categoryId}")
  public ResponseEntity<List<ProductResponseDto>> getProductsByCategoryId(
      @PathVariable Long categoryId) {
    List<ProductResponseDto> products = productService.findByCategoryId(categoryId);
    if (products.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(null); // or return an appropriate message
    }
    return ResponseEntity.ok(products);
  }

  @Operation(summary = "Get all products on sale", description = "Retrieves all products that are currently on sale.")
  @GetMapping("/on-sale")
  public ResponseEntity<List<ProductResponseDto>> getProductsOnSale() {
    List<ProductResponseDto> productsOnSale = productService.findOnSale();
    if (productsOnSale.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return ResponseEntity.ok(productsOnSale);
  }
}
