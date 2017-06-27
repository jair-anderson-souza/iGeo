/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.igeo.webservices;

import io.github.jass2125.igeo.core.entity.Count;
import io.github.jass2125.igeo.core.services.client.UserPrincipalService;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author <a href="mailto:jair_anderson_bs@hotmail.com">Anderson Souza</a>
 * @since Jun 24, 2017 2:16:56 PM
 */
@Path("login")
@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class LoginWebService {

    @EJB
    private UserPrincipalService userService;

    @POST
    public Response login(Count count) {
        Count user = userService.login(count);
        return Response.ok(user).build();
    }

}
