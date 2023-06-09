package com.hitzseb.ecommerce.service;

public interface EmailService {
    public void sendVerificationEmail(String to, String verificationLink);
}
