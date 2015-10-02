/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.apple.laf.AquaInternalFrameManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import exeptions.NoQuotesCreatedYetExeption;
import exeptions.QuoteNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author Ismail Cam
 */
@Path( "quote" )
public class Quote
{

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JsonParser parser = new JsonParser();

    private static Map<Integer, String> quotes = new HashMap()
    {
        {
            put( 1, "Friends are kisses blown to us by angels" );
            put( 2, "Do not take life too seriously. You will never get out of it alive" );
            put( 3, "Behind every great man, is a woman rolling her eyes" );
        }
    };

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public Quote()
    {
    }

    /**
     * Retrieves representation of an instance of rest.Quote
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces( "application/json" )
    @Path( "{id}" )
    public String getJson( @PathParam( "id" ) String id ) throws QuoteNotFoundException, NoQuotesCreatedYetExeption
    {
        int quoteId = Integer.parseInt( id );

        if ( id.equals( "random" ) )
        {
            Random r = new Random();
            int start = 1;
            int end = quotes.size() + 1;
            quoteId = r.nextInt( end - start ) + start;
        }

        if ( quotes.isEmpty() )
        {
            throw new NoQuotesCreatedYetExeption();
        }

        String quote = quotes.get( quoteId );

//        if ( quote == null )
//        {
//            throw new IllegalArgumentException();
//        }
        if ( quotes.containsKey( Integer.parseInt( id ) ) )
        {
            JsonObject response = new JsonObject();
            response.addProperty( "quote", quote );
            return gson.toJson( response );
        }
        else
        {
            throw new QuoteNotFoundException();
        }
    }

    /**
     * PUT method for updating or creating an instance of Quote
     *
     * @param content representation for the resource
     *
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes( "application/json" )
    @Path( "{id}" )
    public String putJson( @PathParam( "id" ) String id, String content ) throws QuoteNotFoundException
    {
        JsonObject request = parser.parse( content ).getAsJsonObject();
        String quote = request.get( "quote" ).getAsString();

        if ( quotes.containsKey( Integer.parseInt( id ) ) )
        {
            quotes.replace( Integer.parseInt( id ), quote );

            JsonObject response = new JsonObject();
            response.addProperty( "id", id );
            response.addProperty( "quote", quote );

            return gson.toJson( response );
        }
        else
        {
            throw new QuoteNotFoundException();
        }
    }

    @POST
    @Consumes( "application/json" )
    public String createPost( String content )
    {
        JsonObject request = parser.parse( content ).getAsJsonObject();
        String quote = request.get( "quote" ).getAsString();
        int id = quotes.size() + 1;

        quotes.put( id, quote );

        JsonObject response = new JsonObject();
        response.addProperty( "id", quotes.size() );
        response.addProperty( "quote", quote );

        return gson.toJson( response );
    }

    @DELETE
    @Path( "{id}" )
    public String deleteQuote( @PathParam( "id" ) String id ) throws QuoteNotFoundException
    {
        JsonObject response = new JsonObject();
        response.addProperty( "quote", quotes.get( Integer.parseInt( id ) ) );
        
        if( quotes.containsKey( Integer.parseInt( id )) )
        {
        quotes.remove( Integer.parseInt( id ) );

        return gson.toJson( response );
        }
        else
        {
            throw new QuoteNotFoundException();
        }
    }
}
