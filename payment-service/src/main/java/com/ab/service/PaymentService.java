package com.ab.service;

import com.ab.domain.PaymentMethod;
import com.ab.model.PaymentOrder;
import com.ab.payload.dto.BookingDTO;
import com.ab.payload.dto.UserDTO;
import com.ab.payload.response.PaymentLinkResponse;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentService {

    PaymentLinkResponse createOrder(UserDTO user , BookingDTO bookingDTO, PaymentMethod paymentMethod) throws RazorpayException, StripeException;

    PaymentOrder getPaymentOrderById(Long id);

    PaymentOrder getPaymentOrderByPaymentId(String paymentId);
    PaymentLink createRazorpayPaymentLink(UserDTO  userDTO, Long amount, Long orderId) throws RazorpayException;

    String createStripePaymentLink(UserDTO  userDTO, Long amount,Long orderId) throws StripeException;

}
