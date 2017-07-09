/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.igeo.webservices;

import io.github.jass2125.igeo.core.entity.UserPrincipal;
import io.github.jass2125.igeo.core.exceptions.ApplicationException;
import io.github.jass2125.igeo.core.services.client.UserPrincipalService;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @since Jun 26, 2017 8:53:49 PM
 */
@Path("userprincipal")
@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
@Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8", MediaType.TEXT_HTML})
public class UserPrincipalWebService {

    @EJB
    private UserPrincipalService userPrincipalService;

    @POST
    public Response createNewUserPrincipal(UserPrincipal userPrincipal) {
        UserPrincipal user = null;
        try {
            user = userPrincipalService.register(userPrincipal);
            return Response.
                    ok(user).
                    build();
        } catch (ApplicationException e) {
            return Response.
                    ok().
                    status(Status.RESET_CONTENT).
                    build();
        } catch (Exception e) {
            return Response.
                    ok(userPrincipal).
                    status(Status.NO_CONTENT).
                    build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteUserPrincipal(@PathParam("id") Long id) {
        try {
            UserPrincipal searchUserPrincipalById = userPrincipalService.searchUserPrincipalById(id);
            userPrincipalService.delete(searchUserPrincipalById);
        } catch (ApplicationException ex) {
            Logger.getLogger(UserPrincipalWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.ok("Ok").build();
    }

}
