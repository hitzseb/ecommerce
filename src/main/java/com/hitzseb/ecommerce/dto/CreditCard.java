package com.hitzseb.ecommerce.dto;

public record CreditCard(String cardholderName, String cardNumber, String expirationDate, int cvv) {
}