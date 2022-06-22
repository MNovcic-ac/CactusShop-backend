package com.example.cactusshop.repository;

import com.example.cactusshop.entity.Order;
import com.example.cactusshop.entity.QOrder;
import com.example.cactusshop.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface OrderRepository extends JpaRepository<Order, String>, QuerydslPredicateExecutor<Order> {

    default Page<Order> getForUser(User user, Pageable pageable) {
        QOrder qOrder = QOrder.order;

        var predicate = qOrder.user.eq(user);

        return findAll(predicate, pageable);
    }

    Order findByStripeId(String stripeId);
}
