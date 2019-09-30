package dev.gilliard.vertxwiki.main;

import dev.gilliard.vertxwiki.database.WikiDatabaseVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;

/**
 * 
 * MainVerticle.java
 *
 * @author Gilliard Macedo
 * @date 23/09/2019
 */
public class MainVerticle extends AbstractVerticle {
	@Override
	public void start(Promise<Void> promise) throws Exception {

		Promise<String> dbVerticleDeployment = Promise.promise();
		vertx.deployVerticle(new WikiDatabaseVerticle(), dbVerticleDeployment);

		dbVerticleDeployment.future().compose(id -> {

			Promise<String> httpVerticleDeployment = Promise.promise();
			vertx.deployVerticle("dev.gilliard.vertxwiki.http.HttpServerVerticle", new DeploymentOptions().setInstances(2),
					httpVerticleDeployment);

			return httpVerticleDeployment.future();

		}).setHandler(ar -> {
			if (ar.succeeded()) {
				promise.complete();
			} else {
				promise.fail(ar.cause());
			}
		});
	}
}