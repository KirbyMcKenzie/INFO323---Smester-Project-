/*
 *  Created by: Kirby McKenzie | ID:6601021
 *  INFO323 Course Project 2016, Phase 1
 */
package servers;

import filters.CorsFilter;
import filters.DebugFilter;
import filters.ExceptionLogger;
import filters.ExceptionMessageHandler;
import java.net.URI;
import java.net.URISyntaxException;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.bridge.SLF4JBridgeHandler;
import resources.ConsignmentResource;
import resources.OrderInventoryResource;
import resources.OrderResource;

/**
 *
 * @author Kirby McKenzie
 */
public class RESTServer {

    public static void main(String[] args) throws URISyntaxException {

        // configure the unifed logger
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        // create a web service configuration
        ResourceConfig config = new ResourceConfig();

        // add a debug filter that prints request details
        config.register(DebugFilter.class);

        // an exception handler that copies exception messages into error responses
        config.register(ExceptionMessageHandler.class);

        // an exception listener that ensures that all exceptions get logged
        config.register(ExceptionLogger.class);

        // add our resource classes
        config.register(OrderResource.class);

        config.register(OrderInventoryResource.class);

        config.register(ConsignmentResource.class);

        //config.register(ConsignmentResource.class);
        config.register(DeclarativeLinkingFeature.class);

        // add the CORS filter to allow AJAX clients to access the service
        config.register(CorsFilter.class);

        // dene the URI that the server will use
        URI baseUri = new URI("http://localhost:8081/");

        // start the server (via the built−in Java HTTP server)
        JdkHttpServerFactory.createHttpServer(baseUri, config);
        System.out.println("Service Ready on http://localhost:8081/");

    } // end main
} // end RESTServer class
