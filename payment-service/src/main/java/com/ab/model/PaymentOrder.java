package com.ab.model;

import com.ab.domain.PaymentMethod;
import com.ab.domain.PaymentOrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private Long amount;
    @Column(nullable = false)
    private PaymentOrderStatus status=PaymentOrderStatus.PENDING;
    @Column(nullable = false)
    private PaymentMethod paymentMethod;
    private String paymentLinkId;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Long bookingId;
    @Column(nullable = false)
    private Long salonId;

}
