package com.hitzseb.ecommerce.service;

import com.hitzseb.ecommerce.model.CreditCard;
import jakarta.servlet.http.HttpSession;

public interface FakeCreditCardService {
    CreditCard generateCreditCardData(HttpSession session);
}
