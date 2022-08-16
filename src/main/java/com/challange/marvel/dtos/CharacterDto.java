package com.challange.marvel.dtos;

import com.arnaudpiroelle.marvel.api.objects.Image;

public record CharacterDto(
    int id,
    String name,
    String description,
    Image thumbnail
) {

}
