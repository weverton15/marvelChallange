package com.challange.marvel.configuration;

import java.util.ArrayList;
import java.util.List;
import com.arnaudpiroelle.marvel.api.objects.Character;
import com.arnaudpiroelle.marvel.api.objects.ref.DataWrapper;
import lombok.Getter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

@Getter
public class RequestCallback implements Callback<DataWrapper<Character>> {

    private final List<Integer> characters = new ArrayList<>();

    @Override
    public void success(DataWrapper<Character> characterDataWrapper, Response response) {
        characters.addAll(characterDataWrapper.getData().getResults().stream().map(Character::getId).toList());
    }

    @Override
    public void failure(RetrofitError retrofitError) {
        throw new UnsupportedOperationException();
    }
}
