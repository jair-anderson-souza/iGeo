/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.igeo.core.filter;

import io.github.jass2125.igeo.core.util.SessionRedis;
import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author <a href="mailto:jair_anderson_bs@hotmail.com">Anderson Souza</a>
 * @since Jun 28, 2017 2:51:18 PM
 */
//@PreMatching
//@Provider
public class RequestSessionFilter implements ContainerRequestFilter {

    private SessionRedis redis;

    public RequestSessionFilter() {
        this.redis = new SessionRedis();
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (!requestContext.getUriInfo().getPath().contains("login")) {
            String token = requestContext.getHeaderString("SigningKey");
            if (token != null) {
                if (redis.getKey(token).length() < 1) {
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                }
            } else {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        }
    }
}
