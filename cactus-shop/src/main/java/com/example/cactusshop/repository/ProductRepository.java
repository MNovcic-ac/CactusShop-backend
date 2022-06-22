package com.example.cactusshop.repository;

import com.example.cactusshop.entity.Product;
import com.example.cactusshop.entity.QProduct;
import com.example.cactusshop.entity.Supplier;
import com.querydsl.core.BooleanBuilder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ProductRepository extends JpaRepository<Product, String>, QuerydslPredicateExecutor<Product> {
    Product findByProductName(String name);

    default Page<Product> getProducts(List<Supplier> suppliers, Pageable pageable){
        QProduct qProduct = QProduct.product;

        BooleanBuilder predicate = new BooleanBuilder();

        if(suppliers!= null && !suppliers.isEmpty()){
            predicate.and(qProduct.supplier.in(suppliers));
        }

        return findAll(predicate, pageable);
    }
}
