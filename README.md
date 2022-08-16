# marvelChallange

Marvel challange.

For this test, you need to generate keys:
  - Marvel (for Marvel Characters API) - https://developer.marvel.com
  - Google (for Google Translate API) - https://console.cloud.google.com/apis/credentials
  
Swagger access:
  - localhost:8080/swagger-ui.html
  
Endpoints Access
  - GetAllCharacters -> http://localhost:8080/marvel/v1/characters
  - GetCharactersId (per language description) -> http://localhost:8080/marvel/v1/characters/{characterId}?language={lang}
  
OBS: For configuration to limit requests in marvel, change variable max-characters in "application.yml"

For all call in Marvel, this project using api https://github.com/ArnaudPiroelle/marvel-api.
