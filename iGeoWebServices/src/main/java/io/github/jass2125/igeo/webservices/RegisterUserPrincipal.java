/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.igeo.webservices;

import io.github.jass2125.igeo.core.entity.UserPrincipal;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author <a href="mailto:jair_anderson_bs@hotmail.com">Anderson Souza</a>
 * @since Jun 26, 2017 8:53:49 PM
 */
@Path("register")
@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class RegisterUserPrincipal {

    @POST
    public Response createNewUserPrincipal(UserPrincipal userPrincipal) {
        System.out.println(userPrincipal);
        return null;
    }

}
