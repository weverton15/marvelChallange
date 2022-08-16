package com.challange.marvel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.arnaudpiroelle.marvel.api.exceptions.AuthorizationException;
import com.arnaudpiroelle.marvel.api.exceptions.QueryException;
import com.challange.marvel.service.interfaces.MyCharactersService;

@Component
public class CaffeineCacheApp implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(CaffeineCacheApp.class);
    @Autowired
    MyCharactersService myCharactersService;

    @Override
    public void run(String... args) throws QueryException, AuthorizationException {
        LOG.info("Starting the Caffine cache testing process");
        myCharactersService.getAllCharacters();
    }
}
