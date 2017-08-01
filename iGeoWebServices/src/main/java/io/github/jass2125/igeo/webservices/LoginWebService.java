/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.igeo.webservices;

import com.google.gson.Gson;
import io.github.jass2125.igeo.core.entity.Count;
import io.github.jass2125.igeo.core.entity.UserPrincipal;
import io.github.jass2125.igeo.core.exceptions.ApplicationException;
import io.github.jass2125.igeo.core.util.JsonWebToken;
import io.github.jass2125.igeo.core.util.SessionRedis;
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

    private JsonWebToken jsonWebToken;
    private SessionRedis sessionRedis;
    @EJB
    private UserPrincipalService userService;

    public LoginWebService() {
        this.sessionRedis = new SessionRedis();
    }

    @POST
    public Response login(Count count) {
        UserPrincipal user = null;
        try {
            user = userService.login(count);
            jsonWebToken = new JsonWebToken(user.getCount().getEmail());
            String encodeResponse = jsonWebToken.encodeResponse(user.getName(), new Gson().toJson(user));
            sessionRedis.createKey(jsonWebToken.getToken(), user.getId().toString());
            return Response.
                    ok(encodeResponse, MediaType.TEXT_PLAIN).
                    header("User", getNameClass(user.getClass().getTypeName())).
                    header("SigningKey", jsonWebToken.getToken()).
                    status(Response.Status.OK)
                    .build();
        } catch (ApplicationException e) {
            e.printStackTrace();
            return Response.
                    status(Response.Status.NO_CONTENT).
                    build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.
                    status(Response.Status.NO_CONTENT).
                    build();
        }
    }

    public String getNameClass(String path) {
        String[] aux = path.split("\\.");
        return aux[aux.length - 1].toLowerCase();
    }
}
