package com.hitzseb.ecommerce.service;

import com.hitzseb.ecommerce.dto.CreditCard;

public interface CreditCardValidationService {
    boolean validateCreditCard(CreditCard creditCard);
}
