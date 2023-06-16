package com.hitzseb.ecommerce.service;

import com.hitzseb.ecommerce.enums.Role;

public interface FakeUserService {
    public void createFakeUser(String name, String username, String password, Role role);
    public void createFakeUsers (int amount);
}
