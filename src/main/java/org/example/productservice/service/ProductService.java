package org.example.productservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.productservice.dto.ProductRequest;
import org.example.productservice.dto.ProductResponse;
import org.example.productservice.model.Product;
import org.example.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * Crée un nouveau produit en utilisant les détails fournis dans l'objet ProductRequest.
     *
     * @param productRequest
     *         l'objet ProductRequest contenant les informations nécessaires pour créer un nouveau produit. Cet objet inclut des détails tels que le nom, le
     *         prix et la description du produit.
     */
    public void createProduct(ProductRequest productRequest) {

        Product product = Product.builder().name(productRequest.name()).price(productRequest.price()).description(productRequest.description()).build();

        productRepository.save(product);
        log.info("Product created: {}", product.getId());
    }

    /**
     * Récupère tous les produits disponibles dans le dépôt et les renvoie sous forme de liste de ProductResponse.
     *
     * @return une liste d'objets ProductResponse représentant tous les produits disponibles.
     */
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    /**
     * Convertit un objet Product en un objet ProductResponse.
     *
     * @param product
     *         l'objet Product à convertir. Cet objet contient les informations d'un produit telles que l'identifiant, le nom, la description et le prix.
     * @return un nouvel objet ProductResponse initialisé avec les données provenant de l'objet Product.
     */
    private ProductResponse mapToProductResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }
}
