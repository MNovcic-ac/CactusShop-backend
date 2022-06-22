package com.example.cactusshop.entity;

import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Setter
@ToString
@Table(name = "orders")
@Check(constraints = "(amount >= 0)")
public class Order extends CreatedBase{

    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(columnDefinition = "timestamp with time zone default now()",
            updatable = false, nullable = false)
    private OffsetDateTime orderedAt;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false, unique = true)
    private String stripeId;

    @ManyToOne
    @JoinColumn(name = "fk_order_user")
    private User user;
}
