/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.igeo.webservices;

import io.github.jass2125.igeo.core.entity.Ride;
import io.github.jass2125.igeo.core.exceptions.ApplicationException;
import io.github.jass2125.igeo.core.services.client.RideService;
import java.util.Set;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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

    @DELETE
    @Path("{id}")
    public Response deleteRide(@PathParam("id") Long id) {
        try {
            Ride ride = this.rideService.delete(id);
            return Response
                    .ok(ride, MediaType.APPLICATION_JSON)
                    .build();
        } catch (ApplicationException e) {
            return Response.
                    status(Status.NOT_FOUND).
                    build();
        }
    }

    @PUT
    @Path("{id}")
    public Response updateRide(Ride ride) {
        try {
            Ride rideTemp = this.rideService.updateRide(ride);
            return Response
                    .ok(rideTemp, MediaType.APPLICATION_JSON)
                    .build();
        } catch (ApplicationException e) {
            return Response.
                    ok(ride).
                    status(Status.NO_CONTENT).
                    build();
        }
    }

    @GET
    public Response getAllRides() {
        try {
            Set<Ride> rides = this.rideService.getRides();
            return Response
                    .ok(new GenericEntity<Set<Ride>>(rides){}, MediaType.APPLICATION_JSON)
                    .build();
        } catch (ApplicationException e) {
            return Response.
                    ok().
                    status(Status.NO_CONTENT).
                    build();
        }
    }

    @POST
    @Path("{id}")
    public Response registerRide(Ride ride, @PathParam("id") Long idUserPrincipal) {
        try {
            Ride rideTemp = rideService.register(ride, idUserPrincipal);
            //lembrar de colocar a resposta, ver o filtro do token
            return Response
                    .ok(rideTemp, MediaType.APPLICATION_JSON)
                    .build();
        } catch (ApplicationException e) {
            return Response.
                    ok(ride, MediaType.APPLICATION_JSON).
                    status(Status.NO_CONTENT).
                    build();
        }
    }

}
