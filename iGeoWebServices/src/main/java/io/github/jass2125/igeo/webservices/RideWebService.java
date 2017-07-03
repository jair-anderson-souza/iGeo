/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.igeo.webservices;

import io.github.jass2125.igeo.core.entity.Ride;
import io.github.jass2125.igeo.core.entity.UserPrincipal;
import io.github.jass2125.igeo.core.services.client.RideService;
import io.github.jass2125.igeo.core.services.client.UserPrincipalService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author <a href="mailto:jair_anderson_bs@hotmail.com">Anderson Souza</a>
 * @since Jul 1, 2017 10:26:44 PM
 */
@Path("ride")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class RideWebService {

    @EJB
    private RideService rideService;
    @EJB
    private UserPrincipalService userPrincipalService;

    @POST
    @Path("/{id}")
    public Response registerRide(@PathParam("id") Long id, Ride ride) {
        try {
            //        UserPrincipal userPrincipal = userPrincipalService.searchUserPrincipalById(id);
            userPrincipalService.addRide(id, ride);
        } catch (Exception ex) {
            
        }
        return Response.ok().build();
    }

}
