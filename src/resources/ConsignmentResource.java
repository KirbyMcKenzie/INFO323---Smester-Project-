/*
 *  Created by: Kirby McKenzie | ID:6601021
 *  INFO323 Course Project 2016, Phase 1
 */
package resources;

import dao.OrderDAO;
import domain.Order;
import java.util.Collection;
import java.util.UUID;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author Kirby McKenzie
 */
@Path("/consignment/")
public class ConsignmentResource {

    public OrderDAO dao = new OrderDAO();
    public Order order;
    public Order consignmentID;

    @GET
    @Path("{consignmentID}")
    public Collection<Order> getAllConsignments(@PathParam("consignmentID") String id) {
        return dao.getOrdersInConsignments(id);
    } // end getAllOrders method

    @POST
    public Response createConsignmentId() {

        UUID id = UUID.randomUUID();

        return Response.ok(id).build();

    } // end createConsignmentID

    @POST
    @Path("{consignmentID}")
    public void createConsignment(@PathParam("consignmentID") String id, Order order) {

        dao.addOrderToConsignment(id, order);

    } // end createConsignment

    @PUT
    @Path("{consignmentID}")
    public Response shipConsignment(@PathParam("consignmentID") String id) {
        dao.shipConsignment(id);

        return Response.noContent().build();
    } // end shipConsignment

} // end OrderInventoryResource

