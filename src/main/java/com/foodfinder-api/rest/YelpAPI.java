package com.foodfinder-api.rest;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

public class YelpAPI {
    OAuthService authService;
    Token accessToken;

    public YelpAPI() {
        authService = new ServiceBuilder()
                .provider(YelpAPI10a.class)
                .apiKey(System.getenv("YELP_CONSUMER_KEY"))
                .apiSecret(System.getenv("YELP_CONSUMER_SECRET"))
                .build();
        accessToken = new Token(System.getenv("YELP_TOKEN"), System.getenv("YELP_TOKEN_SECRET");
    }

    public String Search(String searchTerm) {
        OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");
        request.addQuerystringParameter("term", searchTerm);
        request.addQuerystringParameter("location", "Chicago, IL");
        authService.signRequest(accessToken, request);
        Response response = request.send();
        return response.getBody();
    }
}