package test.dev.gilliard.vertxwiki;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import dev.gilliard.vertxwiki.http.HttpServerVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

/**
 * HttpVerticleTest.java
 *
 * @author Gilliard Macedo
 * @date 09/11/2019
 */
@RunWith(VertxUnitRunner.class)
public class HttpVerticleTest {
	
	private Vertx vertx;

	@Before
	public void prepare(TestContext context) throws InterruptedException {
	  vertx = Vertx.vertx();
	  vertx.deployVerticle(new HttpServerVerticle());
	}
	
	@Test
	public void testHttp(TestContext context) {
		
	}
	
	@After
	public void finish(TestContext context) {
	  vertx.close(context.asyncAssertSuccess());
	}

}
