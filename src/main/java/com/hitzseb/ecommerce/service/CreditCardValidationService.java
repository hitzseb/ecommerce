package com.hitzseb.ecommerce.service;

import com.hitzseb.ecommerce.dto.CreditCard;
import org.springframework.stereotype.Service;

@Service
public class CreditCardValidationService {

    public boolean validateCreditCard(CreditCard creditCard) {
        String cardNumber = creditCard.cardNumber();
        String expirationDate = creditCard.expirationDate();
        int cvv = creditCard.cvv();

        String cardNumberRegex = "^(\\d{4}[- ]){3}\\d{4}|\\d{16}$";
        boolean isCardNumberValid = cardNumber.matches(cardNumberRegex);

        String expirationDateRegex = "^(0[1-9]|1[0-2])\\/\\d{2}$";
        boolean isExpirationDateValid = expirationDate.matches(expirationDateRegex);

        boolean isCvvValid = cvv >= 100 && cvv <= 999;

        return isCardNumberValid && isExpirationDateValid && isCvvValid;
    }

}