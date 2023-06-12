package com.hitzseb.ecommerce.model;

public record CreditCard(String cardholderName, String cardNumber, String expirationDate, int cvv) {
}