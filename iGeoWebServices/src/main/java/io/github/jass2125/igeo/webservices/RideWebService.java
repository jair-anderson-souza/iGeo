/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.igeo.webservices;

import io.github.jass2125.igeo.core.entity.Ride;
import io.github.jass2125.igeo.core.exceptions.ApplicationException;
import io.github.jass2125.igeo.core.services.client.RideService;
import io.github.jass2125.igeo.core.services.client.UserPrincipalService;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
            System.out.println("Erro: " + e.getMessage());
            return Response.
                    status(Status.NOT_FOUND).
                    build();
        }
    }

    @POST
    public Response registerRide(Ride ride) {
        try {
            rideService.register(ride);
            //lembrar de colocar a resposta, ver o filtro do token
            return Response
                    .ok(ride, MediaType.APPLICATION_JSON)
                    .build();
        } catch (ApplicationException e) {
            System.out.println(e.getMessage());
            return Response
                    .ok(ride, MediaType.APPLICATION_JSON).
                    build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return Response
                    .ok(ride, MediaType.APPLICATION_JSON).
                    build();
        }
    }

}
