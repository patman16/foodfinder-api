package com.foodfinderapi.rest;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

public class YelpAPI10a extends DefaultApi10a {
    @Override
    public String getAccessTokenEndpoint() {
        return null;
    }

    @Override
    public String getAuthorizationUrl(Token token) {
        return null;
    }

    @Override
    public String getRequestTokenEndpoint() {
        return null;
    }
}