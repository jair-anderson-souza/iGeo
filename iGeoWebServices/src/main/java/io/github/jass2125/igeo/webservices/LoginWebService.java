/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.igeo.webservices;

import com.google.gson.Gson;
import io.github.jass2125.igeo.core.entity.UserPrincipal;
import io.github.jass2125.igeo.core.services.JsonWebToken;
import io.github.jass2125.igeo.core.services.SessionRedis;
import io.github.jass2125.igeo.core.services.client.UserPrincipalService;
import io.github.jass2125.igeo.core.vo.LoginVO;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
    public Response login(LoginVO loginVo) {
        UserPrincipal user = null;
        try {
            user = userService.login(loginVo);
            jsonWebToken = new JsonWebToken(user.getEmail());
            String encodeResponse = jsonWebToken.encodeResponse(user.getName(), new Gson().toJson(user));
            sessionRedis.createKey(jsonWebToken.getToken(), user.getId().toString());
            return Response.
                    ok(encodeResponse, MediaType.APPLICATION_JSON).
                    header("User", getNameClass(user.getClass().getTypeName())).
                    header("SigningKey", jsonWebToken.getToken())
                    .build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.
                    status(Status.NOT_FOUND).
                    build();
        }
    }

    public String getNameClass(String path) {
        String[] aux = path.split("\\.");
        return aux[aux.length - 1].toLowerCase();
    }
}
