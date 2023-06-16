package com.hitzseb.ecommerce.service;

public record CreditCard(String cardholderName, String cardNumber, String expirationDate, int cvv) {
}