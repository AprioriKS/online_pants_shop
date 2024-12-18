package com.example.online_pants_shop.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@SQLDelete(sql = "UPDATE products SET is_deleted = TRUE WHERE id = ?")
@SQLRestriction(value = "is_deleted = FALSE")
@Table(name = "products")
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String color;
    @ManyToMany
    @JoinTable(
            name = "products_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private int stock;
    @Column(length = 3000)
    private String description;
    private String imageUrl;
    @Column(nullable = false)
    private boolean isDeleted = false;
    @Column(nullable = false)
    private boolean isSales = false;
    @Column(nullable = false)
    private String size;
    @Column(nullable = false)
    private int rating;
    private String style;
}
