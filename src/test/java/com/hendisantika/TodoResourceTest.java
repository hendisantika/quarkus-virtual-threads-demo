package com.hendisantika;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit5.virtual.ShouldNotPin;
import io.quarkus.test.junit5.virtual.VirtualThreadUnit;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Created by IntelliJ IDEA.
 * Project : quarkus-virtual-threads-demo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/17/23
 * Time: 19:37
 * To change this template use File | Settings | File Templates.
 */
@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@VirtualThreadUnit
@ShouldNotPin
public class TodoResourceTest {
}
