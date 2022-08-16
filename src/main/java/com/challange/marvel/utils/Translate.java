package com.challange.marvel.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import javax.net.ssl.HttpsURLConnection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class Translate {

    @Value("${translate.google-key}")
    private String googleKey;

    @Value("${translate.url-translate}")
    private String urlTranslate;
    private StringBuilder urlBuilder;

    private void appendURL(String text, Locale locale) {
        urlBuilder = new StringBuilder(urlTranslate);
        urlBuilder.append(googleKey);
        urlBuilder.append("&source=en");
        urlBuilder.append("&target=" + locale);
        urlBuilder.append("&q=" + text);
    }

    public String translate(String text, Locale locale) {
        StringBuilder result = new StringBuilder();
        try {
            String encodedText = URLEncoder.encode(text, StandardCharsets.UTF_8);
            appendURL(encodedText, locale);

            URL url = new URL(urlBuilder.toString());

            var conn = (HttpsURLConnection) url.openConnection();
            InputStream stream;
            if (conn.getResponseCode() == 200) {
                stream = conn.getInputStream();
            } else {
                stream = conn.getErrorStream();
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            JsonElement element = JsonParser.parseString(result.toString());
            var translatedText = "";

            if (element.isJsonObject()) {
                JsonObject obj = element.getAsJsonObject();
                if (obj.get("error") == null) {
                    translatedText = obj.get("data").getAsJsonObject().
                        get("translations").getAsJsonArray().
                        get(0).getAsJsonObject().
                        get("translatedText").getAsString();
                    return translatedText;

                }
            }

            if (conn.getResponseCode() != 200) {
                log.error(result.toString());
            }

        } catch (IOException | JsonSyntaxException ex) {
            log.error(ex.getMessage());
        }

        return null;
    }

}
