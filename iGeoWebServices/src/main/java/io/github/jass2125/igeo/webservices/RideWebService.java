/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.igeo.webservices;

import io.github.jass2125.igeo.core.entity.Ride;
import io.github.jass2125.igeo.core.services.client.RideService;
import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author <a href="mailto:jair_anderson_bs@hotmail.com">Anderson Souza</a>
 * @since Jul 1, 2017 10:26:44 PM
 */
@Path("ride")
public class RideWebService {

    @EJB
    private RideService service;

    @POST
    public Response registerRide(Ride ride) {
        System.out.println(ride);
        service.register(ride);
        return Response.ok().build();
    }
}
