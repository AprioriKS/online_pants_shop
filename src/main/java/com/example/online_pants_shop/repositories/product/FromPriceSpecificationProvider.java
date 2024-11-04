package com.example.online_pants_shop.repositories.product;

import com.example.online_pants_shop.entities.Product;
import java.math.BigDecimal;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class FromPriceSpecificationProvider implements ProductSpecificationProvider<Product> {
    @Override
    public String getKey() {
        return "fromPrice";
    }

    @Override
    public Specification<Product> getSpecification(String[] params) {
        BigDecimal price = new BigDecimal(params[0]);
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.ge(root.get("price"), price));
    }
}
