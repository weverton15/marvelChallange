package com.challange.marvel.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Locale;
import com.arnaudpiroelle.marvel.api.exceptions.AuthorizationException;
import com.arnaudpiroelle.marvel.api.exceptions.EntityNotFoundException;
import com.arnaudpiroelle.marvel.api.exceptions.QueryException;
import com.arnaudpiroelle.marvel.api.exceptions.RateLimitException;
import com.challange.marvel.dtos.CharacterDto;
import com.challange.marvel.exception.EntityException;
import com.challange.marvel.service.MyCharactersServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("${routing.base-path}")
@Slf4j
@RequiredArgsConstructor
public class MarvelController {

    private final MyCharactersServiceImpl service;

    @GetMapping("/characters")
    public ResponseEntity<List<Integer>> getAllCharacters() throws QueryException, AuthorizationException, EntityException {
        return new ResponseEntity<>(service.getAllCharacters(), HttpStatus.OK);

    }

    @GetMapping("/characters/{characterId}")
    public ResponseEntity<CharacterDto> getCharactersById(
        @PathVariable(value = "characterId") String characterId,
        @RequestParam(value = "language", required = false) Locale language
    )
        throws QueryException, RateLimitException, AuthorizationException, EntityException, EntityNotFoundException {

        return new ResponseEntity<>(service.getCharacters(characterId, language), HttpStatus.OK);
    }

}
