package com.hendisantika.virtualthreads.controller;

import com.hendisantika.virtualthreads.entity.Todo;
import io.quarkus.logging.Log;
import io.quarkus.panache.common.Sort;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

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

}
