package com.example.online_pants_shop.repositories.product;

import com.example.online_pants_shop.entities.Product;
import java.math.BigDecimal;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ToPriceSpecificationProvider implements ProductSpecificationProvider<Product> {
    @Override
    public String getKey() {
        return "toPrice";
    }

    @Override
    public Specification<Product> getSpecification(String[] params) {
        BigDecimal price = new BigDecimal(params[0]);
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.le(root.get("price"), price));
    }
}
