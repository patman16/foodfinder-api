package com.foodfinderapi.rest;

import com.foodfinderapi.models.YelpBusiness;

import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/business")
@ApplicationScoped
public class YelpBusinessEndpoint {

    @GET
    @Path("/{latitude}/{longitude}/{mileRadius}/{searchTerm}")
    @Produces(MediaType.APPLICATION_JSON)
    public YelpBusiness[] get(@PathParam("latitude") double latitude, @PathParam("longitude") double longitude, @PathParam("mileRadius") int mileRadius, @PathParam("searchTerm") String searchTerm){
		if (latitude == 0.0 && longitude == 0.0)
            return new YelpBusiness[0];
	
        YelpAPI api = new YelpAPI();
        String response = api.Search(latitude, longitude, mileRadius, searchTerm);
        YelpParser parser = new YelpParser();
        ArrayList<YelpBusiness> businessResults = parser.parseBusinesses(response);
        return businessResults.toArray(new YelpBusiness[0]);
    }
}