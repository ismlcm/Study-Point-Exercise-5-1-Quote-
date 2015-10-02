/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import exeptions.NoQuotesCreatedYetExeption;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * 
 * @author Ismail Cam
 */
@Provider
public class NoQuotesCreatedYetExMapper implements ExceptionMapper<NoQuotesCreatedYetExeption>{

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    @Override
    public Response toResponse( NoQuotesCreatedYetExeption e )
    {
        JsonObject obj = new JsonObject();
        obj.addProperty( "code", "404");
        obj.addProperty( "msg", "No Quotes Created yet");
        return Response.status( Response.Status.NOT_FOUND ).entity( gson.toJson( obj ) ).type( MediaType.APPLICATION_JSON ).build();
    }

}
