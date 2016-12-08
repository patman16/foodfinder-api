package com.foodfinder-api.rest;

import com.foodfinder-api.models.YelpBusiness;

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
  @Path("/{searchTerm}")
  @Produces(MediaType.APPLICATION_JSON)
  public YelpBusiness[] get(@PathParam("searchTerm") String searchTerm){
    YelpAPI api = new YelpAPI();
	String response = api.Search(searchTerm);
    YelpParser parser = new YelpParser();
	ArrayList<YelpBusiness> businessResults = parser.parseBusinesses(response);
	return businessResults.toArray(new YelpBusiness[0]);
  }
}