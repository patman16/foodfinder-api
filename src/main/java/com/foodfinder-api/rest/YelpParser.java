package com.foodfinder-api.rest;

import com.foodfinder-api.models.YelpBusiness;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class YelpParser {
    public ArrayList<YelpBusiness> parseBusiness(String yelpResponse) throws JSONException {
		ArrayList<YelpBusiness> yelpBusinesses = new ArrayList<YelpBusiness>();
        JSONObject responseObject = new JSONObject(yelpResponse);
        JSONArray businesses = responseObject.getJSONArray("businesses");
        for (int i = 0; businesses.length() > i; i++) {
            YelpBusiness business = new YelpBusiness();
            JSONObject businessJSON = businesses.getJSONObject(i);
            business.name = businessJSON.get("name").toString();
            JSONObject location = businessJSON.getJSONObject("location");
            JSONObject coordinate = location.getJSONObject("coordinate");
            business.address = location.getJSONArray("display_address").get(0).toString();
            business.latitude = coordinate.getDouble("latitude");
            business.longitude = coordinate.getDouble("longitude");
            business.starRating = businessJSON.getDouble("rating");
            yelpBusinesses.add(business);
        }
		
		return yelpBusinesses;
    }
}
