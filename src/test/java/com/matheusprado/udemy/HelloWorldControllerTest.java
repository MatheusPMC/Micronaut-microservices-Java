package com.matheusprado.udemy;

import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import javax.inject.Inject;

@MicronautTest
class HelloWorldControllerTest {

    @Inject
    EmbeddedApplication<?> application;

    @Inject
    @Client("/") RxHttpClient client;

    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }

    @Test
    void testHelloResponse() {
        final String result = client.toBlocking().retrieve("/hello");
        Assertions.assertEquals("Hello from Services!", result);
    }

    @Test
    void retunsGermanGreenting() {
       final String result = client.toBlocking().retrieve("/hello/de");
        Assertions.assertEquals("Hallo", result);
    }

    @Test
    void retunsEnglishGreenting() {
        final String result = client.toBlocking().retrieve("/hello/en");
        Assertions.assertEquals("Hello", result);
    }
}
