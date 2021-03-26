package com.matheusprado.udemy;

import com.matheusprado.udemy.auth.persistence.UserEntity;
import com.matheusprado.udemy.auth.persistence.UserRepository;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;

import javax.inject.Singleton;

@Singleton
public class TestDataProvider {
    private final UserRepository users;

    public TestDataProvider(UserRepository users) {
        this.users = users;
    }

    @EventListener
    public void init(StartupEvent event) {
        final String email = "matheus@example.com";
        if (users.findByEmail(email).isEmpty()) {
            final UserEntity matheus = new UserEntity();
            matheus.setEmail(email);
            matheus.setPassword("secret");
            users.save(matheus);
        }
    }
}
