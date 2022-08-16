package com.challange.marvel.service.interfaces;

import java.util.List;
import java.util.Locale;
import com.arnaudpiroelle.marvel.api.exceptions.AuthorizationException;
import com.arnaudpiroelle.marvel.api.exceptions.EntityNotFoundException;
import com.arnaudpiroelle.marvel.api.exceptions.QueryException;
import com.arnaudpiroelle.marvel.api.exceptions.RateLimitException;
import com.challange.marvel.dtos.CharacterDto;

public interface MyCharactersService {

    List<Integer> getAllCharacters() throws QueryException, AuthorizationException;

    CharacterDto getCharacters(String characterId, Locale locale)
        throws QueryException, RateLimitException, AuthorizationException, EntityNotFoundException;
}
