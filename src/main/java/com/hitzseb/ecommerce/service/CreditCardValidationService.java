package com.hitzseb.ecommerce.service;

import com.hitzseb.ecommerce.model.CreditCard;

public interface CreditCardValidationService {
    boolean validateCreditCard(CreditCard creditCard);
}
