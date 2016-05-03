/*
 *  Created by: Kirby McKenzie | ID:6601021
 *  INFO323 Course Project 2016, Phase 1
 */
package resources;

import dao.OrderDAO;
import domain.Order;
import java.net.URI;
import java.util.Collection;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Kirby McKenzie
 */
@Path("/orders/")
public class OrderInventoryResource {

    public OrderDAO dao = new OrderDAO();
    public Order order;

    @GET
    public Collection<Order> getAllOrders() {
        return dao.getAll();
    } // end getAllOrders method

    @GET
    @Path("/status/unfilled")
    public Collection<Order> getUnfilledOrders() {
        return dao.getUnfilledOrders();
    } // end getAllOrders method

    @POST
    public Response createOrder(Order order, @Context UriInfo uri) {
        dao.create(order);
        //dao.addOrderToConsignment(order.getOrderID(), order);

        URI newURI = uri.getAbsolutePathBuilder()
                .path(order.getOrderID())
                .build();

        return Response.created(newURI).build();
    }

} // end OrderInventoryResource
