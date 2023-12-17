package com.hendisantika.virtualthreads.controller;

import com.hendisantika.virtualthreads.entity.Todo;
import io.quarkus.logging.Log;
import io.quarkus.panache.common.Sort;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : quarkus-virtual-threads-demo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/17/23
 * Time: 16:33
 * To change this template use File | Settings | File Templates.
 */

@Path("/api")
@RunOnVirtualThread
public class TodoController {
    private void log() {
        Log.infof("Called on %s", Thread.currentThread());
    }

    @GET
    public List<Todo> getAll() {
        log();
        // pinTheCarrierThread(); // To demonstrate pinning detection
        return Todo.listAll(Sort.by("order"));
    }

    @GET
    @Path("/{id}")
    public Todo getOne(@PathParam("id") Long id) {
        log();
        Todo entity = Todo.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Todo with id of " + id + " does not exist.", Response.Status.NOT_FOUND);
        }
        return entity;
    }

    @POST
    @Transactional
    public Response create(@Valid Todo item) {
        log();
        item.persist();
        return Response.status(Response.Status.CREATED).entity(item).build();
    }


    @PATCH
    @Path("/{id}")
    @Transactional
    public Response update(@Valid Todo todo, @PathParam("id") Long id) {
        log();
        Todo entity = Todo.findById(id);
        entity.id = id;
        entity.completed = todo.completed;
        entity.order = todo.order;
        entity.title = todo.title;
        entity.url = todo.url;
        return Response.ok(entity).build();
    }

    @DELETE
    @Transactional
    public Response deleteCompleted() {
        log();
        Todo.deleteCompleted();
        return Response.noContent().build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response deleteOne(@PathParam("id") Long id) {
        log();
        Todo entity = Todo.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Todo with id of " + id + " does not exist.", Response.Status.NOT_FOUND);
        }
        entity.delete();
        return Response.noContent().build();
    }

    private void pinTheCarrierThread() {
        synchronized (this) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ignored) {
                // For testing purpose only.
            }
        }
    }
}
