package com.hendisantika.virtualthreads.controller;

/**
 * Created by IntelliJ IDEA.
 * Project : quarkus-virtual-threads-demo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/17/23
 * Time: 16:32
 * To change this template use File | Settings | File Templates.
 */

import io.smallrye.common.annotation.NonBlocking;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;

@Path("/")
public class IndexController {
    @GET
    @NonBlocking
    public Response redirect() {
        URI redirect = UriBuilder.fromUri("todo.html").build();
        return Response.temporaryRedirect(redirect).build();
    }
}
