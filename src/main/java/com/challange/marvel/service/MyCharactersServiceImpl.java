package com.challange.marvel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.EnumMap;
import java.util.List;
import java.util.Locale;
import com.arnaudpiroelle.marvel.api.MarvelApi;
import com.arnaudpiroelle.marvel.api.exceptions.AuthorizationException;
import com.arnaudpiroelle.marvel.api.exceptions.EntityNotFoundException;
import com.arnaudpiroelle.marvel.api.exceptions.QueryException;
import com.arnaudpiroelle.marvel.api.exceptions.RateLimitException;
import com.arnaudpiroelle.marvel.api.params.name.character.ListCharacterParamName;
import com.arnaudpiroelle.marvel.api.services.async.CharactersAsyncService;
import com.arnaudpiroelle.marvel.api.services.sync.CharactersService;
import com.challange.marvel.configuration.RequestCallback;
import com.challange.marvel.dtos.CharacterDto;
import com.challange.marvel.service.interfaces.MyCharactersService;
import com.challange.marvel.utils.Translate;

@Service
@CacheConfig(cacheNames = {"characters"})
public class MyCharactersServiceImpl implements MyCharactersService {

    @Autowired
    private Translate translate;
    @Value("${marvel.max-characters}")
    private Double maxCharacters;

    @Value("${marvel.api-public-key}")
    private String apiPublicKey;

    @Value("${marvel.api-private-key}")
    private String apiPrivateKey;


    @Override
    @Cacheable(value = "characters")
    public List<Integer> getAllCharacters() throws QueryException, AuthorizationException {
        MarvelApi.configure()
            .withApiKeys(apiPublicKey, apiPrivateKey).init();

        var comicsService = MarvelApi.getService(CharactersAsyncService.class);

        int count = 0;
        var callback = new RequestCallback();
        var parameters = new EnumMap<ListCharacterParamName, String>(ListCharacterParamName.class);
        parameters.put(ListCharacterParamName.LIMIT, "100");

        while (count < maxCharacters) {
            int finalCount = count++;
            parameters.put(ListCharacterParamName.OFFSET, String.valueOf(finalCount));
            comicsService.listCharacter(parameters, callback);
        }

        return callback.getCharacters();
    }

    @Override
    public CharacterDto getCharacters(String characterId, Locale locale)
        throws QueryException, RateLimitException, AuthorizationException, EntityNotFoundException {
        MarvelApi.configure()
            .withApiKeys(apiPublicKey, apiPrivateKey).init();

        var comicsService = MarvelApi.getService(CharactersService.class);

        return comicsService.getCharacter(Integer.valueOf(characterId)).getData().getResults().stream().map(character1 ->
            new CharacterDto(
                character1.getId(),
                character1.getName(),
                (locale == null || Locale.ENGLISH.equals(locale)) ? character1.getDescription()
                    : translate.translate(character1.getDescription(), locale),
                character1.getThumbnail()
            )).toList().get(0);
    }

}
