/*
 *  Created by: Kirby McKenzie | ID:6601021
 *  INFO323 Course Project 2016, Phase 1
 */
package resources;

import dao.OrderDAO;
import domain.Order;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author Kirby McKenzie
 */
@Path("/orders/{orderID}")
public class OrderResource {

    public OrderDAO dao = new OrderDAO();
    public String orderID;
    public Order order;

    public OrderResource(@PathParam("orderID") String orderID) throws NotFoundException {

        if (dao.exists(orderID) == false) {
            throw new NotFoundException("There is no order that matches that ID bruh");

        } else {
            order = dao.getByID(orderID);
            this.orderID = orderID;
        }

    } // end OrderResource Constructor

    @GET
    public Order getOrder() {
        return order;
    }

    @DELETE
    public void deleteOrder() {
        dao.delete(order);

    }

    @PUT
    public Response updateOrder(Order updatedOrder) {
        if (this.orderID.equals(updatedOrder.getOrderID())) {
            dao.updateOrder(this.orderID, updatedOrder);
            return Response.noContent().build();
        } else {
            return Response
                    .status(Response.Status.CONFLICT)
                    .entity("IDs don't match")
                    .build();
        }
    }

    /*
     @PUT
     public Response recieveOrder(@PathParam("orderID") String orderID){
     dao.recieveOrder(orderID);
        
     return Response.noContent().build();
     }
     */
} // end class

