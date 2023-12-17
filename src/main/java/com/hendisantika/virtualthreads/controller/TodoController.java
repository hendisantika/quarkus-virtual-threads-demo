package com.hendisantika.virtualthreads.controller;

import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.ws.rs.Path;

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
}
