package com.matheusprado.udemy.broker.persistence;


import com.matheusprado.udemy.broker.persistence.jpa.SymbolEntity;
import com.matheusprado.udemy.broker.persistence.jpa.SymbolsRepository;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.stream.Stream;

/**
 * Used to insert data intro db on startup
 */
@Singleton
@Requires(notEnv = Environment.TEST)
public class TestDataProvider {

    private static final Logger LOG = LoggerFactory.getLogger(TestDataProvider.class);
    private final SymbolsRepository symbols;

    public  TestDataProvider(final SymbolsRepository symbols) {
        this.symbols = symbols;
    }

    @EventListener
    public void init(StartupEvent event) {
        if (symbols.findAll().isEmpty()) {
            LOG.info("Adding test data as empty database was found!");
            Stream.of("AAPL", "AMZN", "FB", "TSLA")
                    .map(SymbolEntity::new)
                    .forEach(symbols::save);
        }
    }
}
