package com.ab.controller;

import com.ab.domain.PaymentMethod;
import com.ab.model.PaymentOrder;
import com.ab.payload.dto.BookingDTO;
import com.ab.payload.dto.UserDTO;
import com.ab.payload.response.PaymentLinkResponse;
import com.ab.service.PaymentService;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;


    @PostMapping("/create")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(@RequestBody BookingDTO booking , @RequestParam PaymentMethod paymentMethod) throws StripeException, RazorpayException {

        UserDTO user = new UserDTO();
        user.setFullName("Yash");
        user.setEmail("y@gmail.com");
        user.setId(1L);

        PaymentLinkResponse res = paymentService.createOrder(user,booking,paymentMethod);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/{paymentOrderId}")
    public ResponseEntity<PaymentOrder> getPaymentOrderById( @PathVariable Long paymentOrderId) throws StripeException, RazorpayException {


        PaymentOrder res = paymentService.getPaymentOrderById(paymentOrderId);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @PatchMapping("/proceed")
    public ResponseEntity<Boolean> proceedPayment( @RequestParam String paymentId,@RequestParam String paymentLinkId) throws StripeException, RazorpayException {

        PaymentOrder paymentOrder = paymentService.getPaymentOrderByPaymentId(paymentLinkId);

        Boolean res = paymentService.proceedPayment(paymentOrder, paymentId, paymentLinkId);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}
