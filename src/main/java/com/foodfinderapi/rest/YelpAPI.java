package com.foodfinderapi.rest;

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
        accessToken = new Token(System.getenv("YELP_TOKEN"), System.getenv("YELP_TOKEN_SECRET"));
    }

    public String Search(double latitude, double longitude, double mileRadius, String searchTerm) {
        String mapLocation = String.format("%1$.4f,%2$.4f", latitude, longitude);

        OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");
        request.addQuerystringParameter("term", searchTerm);
		int footRadius = (int)(mileRadius * 1609);
        request.addQuerystringParameter("radius_filter", Integer.toString(footRadius));
        request.addQuerystringParameter("ll", mapLocation);
        authService.signRequest(accessToken, request);
        Response response = request.send();
        return response.getBody();
    }
}
